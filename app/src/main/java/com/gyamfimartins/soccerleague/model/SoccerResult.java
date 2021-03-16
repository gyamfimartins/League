package com.gyamfimartins.soccerleague.model;

public class SoccerResult {
    String GameId,AwayTeamId,AwayTeamName,HomeTeamId,HomeTeamName;
    int AwayScore,HomeScore;

    public String getGameId() {
        return GameId;
    }

    public String getAwayTeamId() {
        return AwayTeamId;
    }

    public String getAwayTeamName() {
        return AwayTeamName;
    }

    public String getHomeTeamId() {
        return HomeTeamId;
    }

    public String getHomeTeamName() {
        return HomeTeamName;
    }

    public int getAwayScore() {
        return AwayScore;
    }

    public int getHomeScore() {
        return HomeScore;
    }
}
