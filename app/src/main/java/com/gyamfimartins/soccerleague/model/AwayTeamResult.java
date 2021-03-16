package com.gyamfimartins.soccerleague.model;

import java.util.List;

public class AwayTeamResult {
    private String AwayTeamName;
    private List<SoccerResult> soccerResults;

    public String getAwayTeamName() {
        return AwayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        AwayTeamName = awayTeamName;
    }

    public List<SoccerResult> getSoccerResults() {
        return soccerResults;
    }

    public void setSoccerResults(List<SoccerResult> soccerResults) {
        this.soccerResults = soccerResults;
    }
}
