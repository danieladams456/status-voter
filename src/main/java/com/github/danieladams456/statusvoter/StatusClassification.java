package com.github.danieladams456.statusvoter;

/**
 * We are using an explicitly assigned score instead of order-dependent ordinal().
 * Score values are not leaked as visibility is package-private.
 * Scores should be unique so there is always a winner on merge.
 */
public enum StatusClassification {
    INITIAL(0),
    SUCCESS(10),
    UNCLEAR_ATTRIBUTION_ERROR(20),
    CUSTOMER_DATA_ERROR(30),
    CUSTOMER_DATA_VALIDATION_ERROR(40),
    INTERNAL_STATUS_MERGE_ERROR(50),
    INTERNAL_ERROR(60);

    private final int score;

    StatusClassification(int score) {
        this.score = score;
    }

    int getScore() {
        return score;
    }
}
