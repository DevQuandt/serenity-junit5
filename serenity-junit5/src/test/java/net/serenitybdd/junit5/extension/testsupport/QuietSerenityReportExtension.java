package net.serenitybdd.junit5.extension.testsupport;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import net.thucydides.core.steps.StepEventBus;

public class QuietSerenityReportExtension implements AfterAllCallback {

    @Override
    public void afterAll(@NotNull final ExtensionContext context) {
        StepEventBus.getEventBus().reset();
    }

}
