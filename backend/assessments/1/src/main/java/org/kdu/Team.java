package org.kdu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Team {
    private String name;
    private ArrayList<Player> players;
    private String home;

    public Team(String name, ArrayList<Player> players, String home) {
        this.name = name;
        this.players = players;
        this.home = home;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
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

    public String printPlayerList(){
        String result = "";
        players.forEach(player -> result.concat(player.toString() + "\n"));
        return result;
    }

    public List<Player> getBowlersbyTeam()
    {
        return players.stream().filter(player -> player.getWickets() >= 40).toList();
    }

    public List<Player> getHighestWicketTaker()
    {
        List<Player> playerList =  players.stream().sorted(new Comparator<Player>() {
            @Override
            public int compare(Player player, Player t1) {
                return Integer.compare(t1.getWickets(),player.getWickets());
            }
        }).toList();

        int highest = playerList.get(0).getWickets();

        return playerList.stream().filter(player -> player.getWickets() == highest).toList();
    }

    public List<Player> getTopNWicketTaker(int n)
    {
        return players.stream().sorted(new Comparator<Player>() {
            @Override
            public int compare(Player player, Player t1) {
                return Integer.compare(t1.getWickets(),player.getWickets());
            }
        }).limit(n).toList();
    }

    public List<Player> getHighestRunScorer()
    {
        List<Player> playerList =  players.stream().sorted(new Comparator<Player>() {
            @Override
            public int compare(Player player, Player t1) {
                return Integer.compare(t1.getRuns(),player.getRuns());
            }
        }).toList();

        int highest = playerList.get(0).getRuns();

        return playerList.stream().filter(player -> player.getRuns() == highest).toList();
    }

    public List<Player> topNRunScorer(int n)
    {
       return players.stream().sorted(new Comparator<Player>() {
            @Override
            public int compare(Player player, Player t1) {
                return Integer.compare(t1.getRuns(),player.getRuns());
            }
        }).limit(n).toList();

    }


}
