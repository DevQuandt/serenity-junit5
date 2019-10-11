package starter.steps;

import org.junit.jupiter.api.Assertions;

import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class MathWizSteps {

    int actor;

    @Step("#actor starts with {0}")
    public void startsWith(final int amount) {
        actor = amount;
    }

    @Step("#actor adds {0}")
    public void adds(final int amount) {
        actor = actor + amount;
    }

    @Step("#actor should have {0}")
    public void shouldHave(final int expectedTotal) {
        then(actor).isEqualTo(expectedTotal);
    }

    @Step
    public void justFail() {
        Assertions.fail("Just failing");
    }

    @Step
    public void failingAssumption() {
        assumeTrue(false, "Failing assumption in step!");
    }
}
