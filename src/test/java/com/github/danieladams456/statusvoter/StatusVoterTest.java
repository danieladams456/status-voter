package com.github.danieladams456.statusvoter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusVoterTest {
    @Test
    void testStatusInit(){
        StatusVoter voter = new StatusVoter();
        assertThat(voter).hasToString("INITIAL");
    }
}
