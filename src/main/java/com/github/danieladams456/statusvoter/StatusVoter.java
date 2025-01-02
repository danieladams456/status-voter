package com.github.danieladams456.statusvoter;

public class StatusVoter {
    private StatusClassification classification;

    public StatusVoter(){
        classification = StatusClassification.INITIAL;
    }

    @Override
    public String toString(){
        return classification.name();
    }
}
