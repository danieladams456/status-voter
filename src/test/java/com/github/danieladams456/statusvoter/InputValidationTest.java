package com.github.danieladams456.statusvoter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class InputValidationTest {
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"invalid", "UNRECOGNIZED_STATUS_ERROR"})
    void stringUpdateMethod(String invalidStatus) {
        StatusVoter voter = new StatusVoter();
        voter.update(invalidStatus);
        assertThat(voter.getClassification()).isEqualTo(StatusClassification.UNRECOGNIZED_STATUS_ERROR);
    }

    @ParameterizedTest
    @NullSource
    @EnumSource(names = "UNRECOGNIZED_STATUS_ERROR")
    void enumUpdateMethod(StatusClassification invalidStatus) {
        StatusVoter voter = new StatusVoter();
        voter.update(invalidStatus);
        assertThat(voter.getClassification()).isEqualTo(StatusClassification.UNRECOGNIZED_STATUS_ERROR);
    }
}
