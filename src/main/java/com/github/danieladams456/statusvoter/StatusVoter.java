package com.github.danieladams456.statusvoter;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public class StatusVoter {
    private StatusClassification classification;

    public StatusVoter() {
        classification = StatusClassification.INITIAL;
    }

    /**
     * Merges the incoming classification with the existing status classification.
     * The status classification with the higher score is retained.
     *
     * @param newClassification the new, incoming classification to merge with the existing classification
     */
    public void merge(StatusClassification newClassification) {
        if (null == newClassification) {
            classification = StatusClassification.UNRECOGNIZED_STATUS_ERROR;
        } else if (newClassification.getScore() > classification.getScore()) {
            classification = newClassification;
        }
    }

    /**
     * Variety that takes an incoming string and hands off to the main merging logic.
     * Can result in UNRECOGNIZED_STATUS_ERROR if not a valid classification.
     * <p>
     * I would rather have all null handling in the enum method, but .valueOf() throws NPE first.
     *
     * @param newClassification the new, incoming classification to merge with the existing classification
     */
    public void merge(String newClassification) {
        try {
            merge(StatusClassification.valueOf(newClassification));
        } catch (IllegalArgumentException | NullPointerException e) {
            merge(StatusClassification.UNRECOGNIZED_STATUS_ERROR);
        }
    }

    /**
     * toString() is also used for JSON serialization
     *
     * @return the string representation of the current StatusClassification
     */
    @JsonValue
    @Override
    public String toString() {
        return classification.name();
    }
}
