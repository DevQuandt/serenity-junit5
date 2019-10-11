package net.serenitybdd.junit5.extension.testsupport;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import net.thucydides.core.steps.BaseStepListener;
import net.thucydides.core.steps.StepEventBus;

/**
 * Auxiliary extension to provide access to the TestOutcomes for validation.
 */
public class TestOutcomeAccessorExtension implements BeforeAllCallback, AfterAllCallback {

    @Override
    public void beforeAll(final ExtensionContext context) {
        TestOutcomeAccessor.resetTestOutcomes();
    }

    @Override
    public void afterAll(@NotNull final ExtensionContext context) {
        final BaseStepListener baseStepListener = StepEventBus.getEventBus().getBaseStepListener();
        TestOutcomeAccessor.registerTestOutcomes(baseStepListener.getTestOutcomes());
    }
}
