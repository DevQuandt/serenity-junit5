package net.serenitybdd.junit5.extension.testsupport;

import java.lang.reflect.Method;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;

public class QuietSerenityLoggingExtension implements InvocationInterceptor {

    private static final String SERENITY_LOGGING = "serenity.logging";

    @Override
    public void interceptTestMethod(
        final Invocation<Void> invocation,
        final ReflectiveInvocationContext<Method> invocationContext,
        final ExtensionContext extensionContext) throws Throwable {
        final EnvironmentVariables environmentVariables = Injectors.getInjector().getProvider(EnvironmentVariables.class).get();
        final String originalValue = environmentVariables.getProperty("serenity.logging");
        try {
            // not an "official" level from serenity-model, only possible because we have a duplicate
            // of the LoggingLevel class here in the test module ;-(
            environmentVariables.setProperty(SERENITY_LOGGING, "OFF");
            invocation.proceed();
        } finally {
            if (originalValue != null) {
                environmentVariables.setProperty(SERENITY_LOGGING, originalValue);
            } else {
                environmentVariables.clearProperty(SERENITY_LOGGING);
            }
        }
    }

}
