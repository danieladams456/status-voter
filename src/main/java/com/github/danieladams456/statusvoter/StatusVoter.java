package com.github.danieladams456.statusvoter;

public class StatusVoter {
    private StatusClassification classification;

    public StatusVoter(){
        classification = StatusClassification.INITIAL;
    }

    public StatusClassification getClassification(){
        return classification;
    }

    /**
     * Updates the current status classification if the incoming classification
     * has a higher score than the existing classification.
     *
     * @param newClassification the new, incoming classification to merge with the existing classification
     */
    public void update(StatusClassification newClassification){
        if (newClassification.getScore() > classification.getScore()){
            classification = newClassification;
        }
    }

    @Override
    public String toString(){
        return classification.name();
    }
}
