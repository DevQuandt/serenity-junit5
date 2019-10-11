package net.serenitybdd.junit5.extension.testsupport;

import org.openqa.selenium.WebDriver;

public class Assertions extends org.assertj.core.api.Assertions {

    public static WebDriverAssert assertThat(final WebDriver actual) {
        return WebDriverAssert.assertThat(actual);
    }
}
