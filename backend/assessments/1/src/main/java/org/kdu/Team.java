package org.kdu;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private ArrayList<Player> players;
    private String home;

    public static final LoggingSystem ls = new LoggingSystem();
    public Team(String name, List<Player> players, String home) {
        this.name = name;
        this.players = (ArrayList<Player>) players;
        this.home = home;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = (ArrayList<Player>) players;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", players=" + printPlayerList() +
                ", home='" + home + '\'' +
                '}';
    }

    public String printPlayerList() {
        String result = "";
        players.forEach(player -> result.concat(player.toString() + "\n"));
        return result;
    }

    public List<Player> getBowlersByTeam() {
        return players.stream().filter(player -> player.getWickets() >= 40).toList();
    }

    public List<Player> getHighestWicketTaker() {
        List<Player> playerList = players.stream().sorted((player, t1) -> Integer.compare(t1.getWickets(), player.getWickets())).toList();
        Player player = playerList.get(0);
        if(player == null){
            ls.logWarn("No such player exist in sorted list");
            return new ArrayList<>();
        }
        int highest = player.getWickets();

        return playerList.stream().filter(player1 -> player1.getWickets() == highest).toList();
    }

    public List<Player> getTopNWicketTaker(int n) {
        return players.stream().sorted((player, t1) -> Integer.compare(t1.getWickets(), player.getWickets())).limit(n).toList();
    }

    public List<Player> getHighestRunScorer() {
        List<Player> playerList = players.stream().sorted((player, t1) -> Integer.compare(t1.getRuns(), player.getRuns())).toList();

        Player player = playerList.get(0);
        if(player == null){
            ls.logWarn("No such player exist in sorted list");
            return new ArrayList<>();
        }
        int highest = player.getRuns();

        return playerList.stream().filter(player1 -> player1.getRuns() == highest).toList();
    }

    public List<Player> topNRunScorer(int n) {
        return players.stream().sorted((player, t1) -> Integer.compare(t1.getRuns(), player.getRuns())).limit(n).toList();

    }


}
