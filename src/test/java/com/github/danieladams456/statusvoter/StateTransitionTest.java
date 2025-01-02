package com.github.danieladams456.statusvoter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StateTransitionTest {
    @Test
    void testStatusInit(){
        StatusVoter voter = new StatusVoter();
        assertThat(voter).hasToString("INITIAL");
    }

    @Test
    void testStatusMoveUp(){
        StatusVoter voter = new StatusVoter();
        voter.update(StatusClassification.CUSTOMER_DATA_VALIDATION_ERROR);
        assertThat(voter).hasToString("CUSTOMER_DATA_VALIDATION_ERROR");
    }

    @Test
    void testStatusNoMoveDown(){
        StatusVoter voter = new StatusVoter();
        voter.update(StatusClassification.CUSTOMER_DATA_VALIDATION_ERROR);
        voter.update(StatusClassification.UNCLEAR_ATTRIBUTION_ERROR);
        assertThat(voter).hasToString("CUSTOMER_DATA_VALIDATION_ERROR");
    }
}
