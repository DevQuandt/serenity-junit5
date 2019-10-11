package net.serenitybdd.junit5.extension.page;

import net.thucydides.core.annotations.TestCaseAnnotations;
import net.thucydides.core.webdriver.Configuration;

@SuppressWarnings("checkstyle:FinalClass")
public class TestConfiguration {

    private final Class<?> testClass;
    private final Configuration configuration;

    private TestConfiguration(final Class<?> testClass, final Configuration configuration) {
        this.testClass = testClass;
        this.configuration = configuration;
    }

    public static TestConfigurationBuilder forClass(final Class<?> testClass) {
        return new TestConfigurationBuilder(testClass);
    }

    public boolean shouldClearTheBrowserSession() {
        return (isAWebTest() && TestCaseAnnotations.shouldClearCookiesBeforeEachTestIn(testClass));
    }

    public boolean isAWebTest() {
        return TestCaseAnnotations.isWebTest(testClass);
    }

    public static class TestConfigurationBuilder {

        private final Class<?> testClass;

        public TestConfigurationBuilder(final Class<?> testClass) {
            this.testClass = testClass;
        }

        public TestConfiguration withSystemConfiguration(final Configuration configuration) {
            return new TestConfiguration(testClass, configuration);
        }
    }

}
