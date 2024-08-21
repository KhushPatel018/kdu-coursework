package org.kdu;

public class Player {
    private String name;
    private Team team;
    private String role;
    private int matches;
    private int runs;
    private int wickets;
    private double strikeRate;
    private double average;

    public Player(String name, Team team, String role, int matches, int runs, int wickets, double strikeRate, double average) {
        this.name = name;
        this.team = team;
        this.role = role;
        this.matches = matches;
        this.runs = runs;
        this.wickets = wickets;
        this.strikeRate = strikeRate;
        this.average = average;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", team=" + team.getName() +
                ", role=" + role +
                ", matches=" + matches +
                ", runs=" + runs +
                ", wickets=" + wickets +
                ", strikeRate=" + strikeRate +
                ", average=" + average +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
