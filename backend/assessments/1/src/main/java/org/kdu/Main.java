package org.kdu;

import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    private static final LoggingSystem ls = new LoggingSystem();
    private static final Scanner sc = new Scanner(System.in);

    private static final TeamRepository TEAM_REPOSITORY = new TeamRepository();



    public static void main(String[] args) {

        TEAM_REPOSITORY.loadCsv(Constants.IPL_DATA_FILE);

        String teamName;
        int choice;
        do {
            String options = "Available Tasks : \n 1. Given the team's name, return all the bowlers who have taken at least 40\n";
            ls.logInfo(options +
                    "wickets.\n" +
                    "2. Given a team display the details of the highest wicket-taker and highest\n" +
                    "run-scorer\n" +
                    "3. Fetch the top 3 run-scorer and top 3 wicket-takers of the season.\n"+
            "4. Write in CSV Fixtures\n" + "5.EXIT");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> {
                    ls.logInfo("Give a team name : ");
                    teamName = sc.nextLine();
                    if (TEAM_REPOSITORY.getTeams().get(teamName) == null) {
                        ls.logWarn("no team found");
                        return;
                    }
                    ls.logInfo("Results : Bowlers who have taken at least 40 for" + teamName);
                    TEAM_REPOSITORY.getTeams().get(teamName).getBowlersByTeam().forEach(player -> ls.logInfo(player.toString() + '\n'));
                }
                case 2 -> {
                    ls.logInfo("Give a team name : ");
                    teamName = sc.nextLine();
                    if (TEAM_REPOSITORY.getTeams().get(teamName) == null) {
                        ls.logWarn("no team found");
                    }
                    ls.logInfo("Results : Highest run scores of " + teamName);
                    TEAM_REPOSITORY.getTeams().get(teamName).getHighestRunScorer().forEach(player -> ls.logInfo(player.toString() + '\n'));
                    ls.logInfo("Results : top wickets takers of " + teamName);
                    TEAM_REPOSITORY.getTeams().get(teamName).getHighestWicketTaker().forEach(player -> ls.logInfo(player.toString() + '\n'));
                }
                case 3 -> TEAM_REPOSITORY.getTop3s();
                case 4 -> {
                    List<String[]> list = TEAM_REPOSITORY.getFixture();
                    ls.logInfo(list.size() + "");
                    TEAM_REPOSITORY.writeToCSV(list);
                    ls.logInfo("written to csv");
                }
                case 5 -> ls.logInfo("Exiting Crypto Menu. Goodbye!");
                default -> ls.logWarn("Not a valid task");
            }
        } while (choice != 5);
    }
}
