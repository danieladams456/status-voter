package com.github.danieladams456.statusvoter;

import org.junit.jupiter.api.Test;

public class StatusVoterTest {
    @Test
    void testStatusInit(){
        StatusVoter voter = new StatusVoter();
        assert voter.toString().equals("INITIAL");
    }
}
