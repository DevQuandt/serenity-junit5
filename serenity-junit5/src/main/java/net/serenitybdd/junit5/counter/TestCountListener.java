package net.serenitybdd.junit5.counter;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.ThucydidesSystemProperty;
import net.thucydides.core.logging.LoggingLevel;
import net.thucydides.core.model.DataTable;
import net.thucydides.core.model.Story;
import net.thucydides.core.model.TestOutcome;
import net.thucydides.core.statistics.TestCount;
import net.thucydides.core.steps.ExecutedStepDescription;
import net.thucydides.core.steps.StepFailure;
import net.thucydides.core.steps.StepListener;
import net.thucydides.core.util.EnvironmentVariables;

public class TestCountListener implements StepListener {

    private final Logger logger;
    private final EnvironmentVariables environmentVariables;
    private final TestCount testCount;

    protected TestCountListener(
            final EnvironmentVariables environmentVariables,
            final Logger logger,
            final TestCount testCount) {
        this.logger = logger;
        this.environmentVariables = environmentVariables;
        this.testCount = testCount;
    }

    public TestCountListener(final EnvironmentVariables environmentVariables, final TestCount testCount) {
        this(environmentVariables, LoggerFactory.getLogger(Serenity.class), testCount);
    }

    private boolean loggingLevelIsAtLeast(final LoggingLevel minimumLoggingLevel) {
        return (getLoggingLevel().compareTo(minimumLoggingLevel) >= 0);
    }

    protected Logger getLogger() {
        return logger;
    }

    private LoggingLevel getLoggingLevel() {
        final String logLevel = ThucydidesSystemProperty.THUCYDIDES_LOGGING
                .from(environmentVariables, LoggingLevel.NORMAL.name());

        return LoggingLevel.valueOf(logLevel);
    }

    public void testSuiteStarted(final Class<?> storyClass) {
    }

    public void testSuiteStarted(final Story storyOrFeature) {
    }

    public void testSuiteFinished() {
    }

    public void testStarted(final String description) {
        final int currentTestCount = testCount.getNextTest();
        if (loggingLevelIsAtLeast(LoggingLevel.NORMAL)) {
            getLogger().info("TEST NUMBER: {}", currentTestCount);
        }
    }

    @Override
    public void testStarted(final String description, final String id) {
        testStarted(description);
    }

    public void testFinished(final TestOutcome result) {
    }

    public void testRetried() {
    }

    public void stepStarted(final ExecutedStepDescription description) {
    }

    public void skippedStepStarted(final ExecutedStepDescription description) {
    }

    public void stepFailed(final StepFailure failure) {
    }

    public void lastStepFailed(final StepFailure failure) {
    }

    public void stepIgnored() {
    }

    public void stepPending() {
    }

    public void stepPending(final String message) {
    }

    public void stepFinished() {
    }

    public void testFailed(final TestOutcome testOutcome, final Throwable cause) {
    }

    public void testIgnored() {
    }

    @Override
    public void testSkipped() {

    }

    @Override
    public void testPending() {

    }

    @Override
    public void testIsManual() {

    }

    public void notifyScreenChange() {
    }

    public void useExamplesFrom(final DataTable table) {
    }

    @Override
    public void addNewExamplesFrom(final DataTable table) {

    }

    public void exampleStarted(final Map<String, String> data) {
    }

    public void exampleFinished() {
    }

    @Override
    public void assumptionViolated(final String message) {
    }

    @Override
    public void testRunFinished() {

    }
}
