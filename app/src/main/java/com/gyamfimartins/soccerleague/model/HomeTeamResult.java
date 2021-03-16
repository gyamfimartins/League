package com.gyamfimartins.soccerleague.model;

import java.util.List;

public class HomeTeamResult {
    private String HomeTeamName;
    private List<SoccerResult> soccerResults;

    public String getHomeTeamName() {
        return HomeTeamName;
    }

    public List<SoccerResult> getSoccerResults() {
        return soccerResults;
    }

    public void setHomeTeamName(String homeTeamName) {
        HomeTeamName = homeTeamName;
    }

    public void setSoccerResults(List<SoccerResult> soccerResults) {
        this.soccerResults = soccerResults;
    }


}
