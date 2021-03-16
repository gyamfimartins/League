package com.gyamfimartins.soccerleague.model;

public class SoccerResult {
    String GameId,AwayTeamId,AwayTeamName,HomeTeamId,HomeTeamName;
    int AwayScore,HomeScore,wins,losses,draws;

    public String getGameId() {
        return GameId;
    }

    public void setGameId(String gameId) {
        GameId = gameId;
    }

    public String getAwayTeamId() {
        return AwayTeamId;
    }

    public void setAwayTeamId(String awayTeamId) {
        AwayTeamId = awayTeamId;
    }

    public String getAwayTeamName() {
        return AwayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        AwayTeamName = awayTeamName;
    }

    public String getHomeTeamId() {
        return HomeTeamId;
    }

    public void setHomeTeamId(String homeTeamId) {
        HomeTeamId = homeTeamId;
    }

    public String getHomeTeamName() {
        return HomeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        HomeTeamName = homeTeamName;
    }

    public int getAwayScore() {
        return AwayScore;
    }

    public void setAwayScore(int awayScore) {
        AwayScore = awayScore;
    }

    public int getHomeScore() {
        return HomeScore;
    }

    public void setHomeScore(int homeScore) {
        HomeScore = homeScore;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }
}
