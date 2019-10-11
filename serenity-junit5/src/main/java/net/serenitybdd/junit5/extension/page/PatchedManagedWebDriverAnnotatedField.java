package net.serenitybdd.junit5.extension.page;

import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;

import net.thucydides.core.annotations.ClearCookiesPolicy;
import net.thucydides.core.annotations.Fields;
import net.thucydides.core.annotations.InvalidManagedWebDriverFieldException;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.webdriver.WebDriverFacade;

/**
 * TODO: Eine Deprecated-Annotation (@java.lang.Deprecated) und ein Deprecated-Javadoc-Tag (@deprecated) mit Beschreibung müssen
 * immer zusammen verwendet werden.
 */
@Deprecated
@SuppressWarnings({ "checkstyle:FinalClass", "checkstyle:MissingDeprecated"})
public class PatchedManagedWebDriverAnnotatedField {

    private static final String NO_ANNOTATED_FIELD_ERROR = "No WebDriver field annotated with @Managed was found in the test case.";

    private final Field field;

    /**
     * Find the first field in the class annotated with the <b>Managed</b> annotation.
     */
    static Optional<PatchedManagedWebDriverAnnotatedField> findOptionalAnnotatedField(final Class<?> testClass) {

        try {
            return fieldsIn(testClass).stream()
                    .filter(PatchedManagedWebDriverAnnotatedField::isFieldAnnotated)
                    .map(PatchedManagedWebDriverAnnotatedField::new)
                    .findFirst();
        } catch (final NoSuchElementException e) {
            return Optional.empty();
        }
    }

    /**
     * Find the first field in the class annotated with the <b>Managed</b> annotation.
     */
    public static PatchedManagedWebDriverAnnotatedField findFirstAnnotatedField(final Class<?> testClass) {

        final Optional<PatchedManagedWebDriverAnnotatedField> optionalField = findOptionalAnnotatedField(testClass);
        if (optionalField.isPresent()) {
            return optionalField.get();
        } else {
            throw new InvalidManagedWebDriverFieldException(NO_ANNOTATED_FIELD_ERROR);
        }
    }

    public static List<PatchedManagedWebDriverAnnotatedField> findAnnotatedFields(final Class<?> testClass) {

        return Fields.of(testClass)
                .allFields()
                .stream()
                .filter(PatchedManagedWebDriverAnnotatedField::isFieldAnnotated)
                .map(PatchedManagedWebDriverAnnotatedField::new)
                .collect(Collectors.toList());
    }

    public static boolean hasManagedWebdriverField(final Class<?> testClass) {

        try {
            return fieldsIn(testClass).stream().anyMatch(PatchedManagedWebDriverAnnotatedField::isFieldAnnotated);
        } catch (final NoSuchElementException e) {
            return false;
        }
    }

    private static boolean isFieldAnnotated(final Field field) {
        return (fieldIsAnnotatedCorrectly(field) && fieldIsRightType(field));
    }

    private static boolean fieldIsRightType(final Field field) {
        return (WebDriverFacade.class.isAssignableFrom(field.getType()) || field.getType().isAssignableFrom(WebDriver.class));
    }

    private static boolean fieldIsAnnotatedCorrectly(final Field field) {
        return (field.getAnnotation(Managed.class) != null);
    }

    private PatchedManagedWebDriverAnnotatedField(final Field field) {
        this.field = field;
    }

    public void setValue(final Object testCase, final WebDriver manageDriver) {
        try {
            field.setAccessible(true);
            field.set(testCase, manageDriver);
        } catch (final IllegalAccessException e) {
            throw new InvalidManagedWebDriverFieldException(
                    "Could not access or set web driver field: " + field + " - is this field public?",
                    e);
        }
    }

    public WebDriver getValue(final Object testCase) {
        try {
            field.setAccessible(true);
            return (WebDriver) field.get(testCase);
        } catch (final IllegalAccessException e) {
            throw new InvalidManagedWebDriverFieldException(
                    "Could not access or set web driver field: " + field + " - is this field public?",
                    e);
        }
    }

    private static Set<Field> fieldsIn(final Class clazz) {
        return Fields.of(clazz).allFields();
    }

    public boolean isUniqueSession() {
        return field.getAnnotation(Managed.class).uniqueSession();
    }

    ClearCookiesPolicy getClearCookiesPolicy() {
        return field.getAnnotation(Managed.class).clearCookies();
    }

    public String getDriver() {
        return field.getAnnotation(Managed.class).driver();
    }

    public String getOptions() {
        return field.getAnnotation(Managed.class).options();
    }

    public String getName() {
        return field.getName();
    }
}
