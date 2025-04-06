package com.github.danieladams456.statusvoter;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreUniquenessTest {
    /**
     * StatusClassification enum comments state we should only use unique scores,
     * but validate that constraint at build.
     */
    @Test
    void ensureUniqueScoreValues() {
        Set<Integer> seenScores = Arrays.stream(StatusClassification.values())
                .map(StatusClassification::getScore)
                .collect(Collectors.toSet());
        assertThat(seenScores).hasSize(StatusClassification.values().length);
    }
}
