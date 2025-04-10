package com.github.danieladams456.statusvoter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.github.danieladams456.statusvoter.TestData.STATUSES_IN_ORDER;
import static org.assertj.core.api.Assertions.assertThat;

public class StateTransitionTest {

    @Test
    void testStatusInit() {
        StatusVoter voter = new StatusVoter();
        assertThat(voter.getClassification()).isEqualTo(StatusClassification.INITIAL);
        assertThat(voter).hasToString("INITIAL");
    }

    /**
     * Checks all combinations of 3 updates, ensuring the highest priority is picked.
     * This test utilizes a local list for expected orders, checking the scores defined in the enum.
     * <p>
     * Test complexity is O(n^3), but that's fine for a small status set like this.
     */
    @Test
    void testStatusOnlyMovesUp() {
        for (int i = 0; i < STATUSES_IN_ORDER.size(); i++)
            for (int j = 0; j < STATUSES_IN_ORDER.size(); j++)
                for (int k = 0; k < STATUSES_IN_ORDER.size(); k++) {
                    StatusVoter voter = new StatusVoter();
                    voter.merge(STATUSES_IN_ORDER.get(i));
                    voter.merge(STATUSES_IN_ORDER.get(j));
                    voter.merge(STATUSES_IN_ORDER.get(k));

                    var maxIndex = Math.max(Math.max(i, j), k);
                    var expectedClassification = STATUSES_IN_ORDER.get(maxIndex);
                    assertThat(voter.getClassification()).isEqualTo(expectedClassification);
                    assertThat(voter).hasToString(expectedClassification.name());
                }
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"invalid", "INTERNAL_STATUS_MERGE_ERROR"})
    void testInvalidStatusDoesNotRevertHigherPriorityStatus_String(String invalidStatus) {
        StatusVoter voter = new StatusVoter();
        // higher score + severity
        voter.merge(StatusClassification.INTERNAL_ERROR);
        // lower score + severity
        voter.merge(invalidStatus);
        assertThat(voter.getClassification()).isEqualTo(StatusClassification.INTERNAL_ERROR);
    }

    @Test
    void testInvalidStatusDoesNotRevertHigherPriorityStatus_Enum() {
        StatusVoter voter = new StatusVoter();
        // higher score + severity
        voter.merge(StatusClassification.INTERNAL_ERROR);
        // lower score + severity
        voter.merge((StatusClassification) null);
        assertThat(voter.getClassification()).isEqualTo(StatusClassification.INTERNAL_ERROR);
    }
}
