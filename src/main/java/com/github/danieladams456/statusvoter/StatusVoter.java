package com.github.danieladams456.statusvoter;

public class StatusVoter {
    private StatusClassification classification;

    public StatusVoter(){
        classification = StatusClassification.INITIAL;
    }

    /**
     * Temporary implementation with .ordinal()
     *
     * @param newClassification the new, incoming classification to merge with the existing classification.
     */
    public void update(StatusClassification newClassification){
        if (newClassification.ordinal() > classification.ordinal()){
            classification = newClassification;
        }
    }

    @Override
    public String toString(){
        return classification.name();
    }
}
