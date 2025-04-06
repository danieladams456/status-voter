package com.github.danieladams456.statusvoter;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StateTransitionTest {
    private static final List<StatusClassification> statusesInOrder = List.of(
            StatusClassification.INITIAL,
            StatusClassification.SUCCESS,
            StatusClassification.UNCLEAR_ATTRIBUTION_ERROR,
            StatusClassification.CUSTOMER_DATA_ERROR,
            StatusClassification.CUSTOMER_DATA_VALIDATION_ERROR,
            StatusClassification.UNRECOGNIZED_STATUS_ERROR,
            StatusClassification.INTERNAL_ERROR
    );

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
        for (int i = 0; i < statusesInOrder.size(); i++)
            for (int j = 0; j < statusesInOrder.size(); j++)
                for (int k = 0; k < statusesInOrder.size(); k++) {
                    StatusVoter voter = new StatusVoter();
                    voter.merge(statusesInOrder.get(i));
                    voter.merge(statusesInOrder.get(j));
                    voter.merge(statusesInOrder.get(k));

                    var maxIndex = Math.max(Math.max(i, j), k);
                    var expectedClassification = statusesInOrder.get(maxIndex);
                    assertThat(voter.getClassification()).isEqualTo(expectedClassification);
                    assertThat(voter).hasToString(expectedClassification.name());
                }
    }
}
