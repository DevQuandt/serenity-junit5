package net.serenitybdd.junit5.extension.testsupport;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.openqa.selenium.WebDriver;

import net.thucydides.core.webdriver.WebDriverFacade;

@SuppressWarnings("checkstyle:FinalClass")
public class WebDriverAssert extends AbstractAssert<WebDriverAssert, WebDriver> {

    private WebDriverAssert(final WebDriver webDriver) {
        super(webDriver, WebDriverAssert.class);
    }

    public static WebDriverAssert assertThat(final WebDriver actual) {
        return new WebDriverAssert(actual);
    }

    public WebDriverAssert hasDriverName(final String expectedDriverName) {
        asInstanceOf(InstanceOfAssertFactories.type(WebDriverFacade.class)).extracting(WebDriverFacade::getDriverName)
                .as("driver name")
                .isEqualTo(expectedDriverName);
        return this;
    }

    public WebDriverAssert hasSameDriverNameAs(final WebDriver expectedWebDriver) {
        asInstanceOf(InstanceOfAssertFactories.type(WebDriverFacade.class)).extracting(WebDriverFacade::getDriverName)
                .as("driver name")
                .isEqualTo(((WebDriverFacade) expectedWebDriver).getDriverName());
        return this;
    }

    public WebDriverAssert hasOptions(final String expectedOptions) {
        asInstanceOf(InstanceOfAssertFactories.type(WebDriverFacade.class)).extracting("options")
                .as("driver options")
                .isEqualTo(expectedOptions);
        return this;
    }
}
