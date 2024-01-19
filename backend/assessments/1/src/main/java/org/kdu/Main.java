package org.kdu;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    private static final LoggingSystem ls = new LoggingSystem();
    private static final Scanner sc = new Scanner(System.in);
    private static HashMap<String,Team> teams = new HashMap<>();
    private static int count = 0;
    public static void loadCsv(String filePath)
    {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> r = reader.readAll();
            for (int i = 1; i < r.size(); i++) {
                String[] data = r.get(i);
//                Name,Team,Role,Matches,Runs,Average,SR,Wickets
                String name = data[0];
                String team= data[1];
                String role = data[2];
                String matches = data[3];
                String runs= data[4];
                String average= data[5];
                String sr= data[6];
                String wickets= data[7];
                Team newTeam;
                if(teams.get(team) == null){
                    newTeam = new Team(team,new ArrayList<>(),team+"_home");
                    teams.put(team,newTeam);
                    ls.logInfo("team Added" + team);

                }else{
                    newTeam = teams.get(team);
                }
                Player player = new Player(name,newTeam,role,Integer.parseInt(matches.trim()),Integer.parseInt(runs.trim()),Integer.parseInt(wickets.trim()),Double.parseDouble(sr.trim()),Double.parseDouble(average));
                newTeam.getPlayers().add(player);
                ls.logInfo("Player Loaded" + player);
                count++;
            }

        } catch (IOException | CsvException e) {
            ls.logInfo(e.getMessage());
        }
        ls.logInfo(count +" ");
        ls.logInfo("Teams : " + teams.size());
    }
    public static void main(String[] args) {

        loadCsv("/home/hp/code/backend/java fundarmentals/Assesment1/src/main/resources/IPL_2021-data.csv");

        ls.logInfo("Available Tasks : \n 1. Given the team's name, return all the bowlers who have taken at least 40\n" +
                "wickets.\n" +
                "2. Given a team display the details of the highest wicket-taker and highest\n" +
                "run-scorer\n" +
                "3. Fetch the top 3 run-scorer and top 3 wicket-takers of the season.");
        int choice = sc.nextInt();
        sc.nextLine();
        String teamName;
        do{

            switch (choice){
                case 1 -> {
                    ls.logInfo("Give a team name : ");
                    teamName = sc.nextLine();
                    if(teams.get(teamName) == null){
                        ls.logWarn("no team found");
                        return;
                    }
                    ls.logInfo("Results : Bowlers who have taken at least 40 for" + teamName);
                    teams.get(teamName).getBowlersbyTeam().forEach(player -> ls.logInfo(player.toString() + '\n'));
                }
                case 2 ->{
                    ls.logInfo("Give a team name : ");
                    teamName = sc.nextLine();
                    if(teams.get(teamName) == null){
                        ls.logWarn("no team found");
                    }
                    ls.logInfo("Results : Highest run scores of " + teamName);
                    teams.get(teamName).getHighestRunScorer().forEach(player -> ls.logInfo(player.toString() + '\n'));
                    ls.logInfo("Results : top wickets takers of " + teamName);
                    teams.get(teamName).getHighestWicketTaker().forEach(player -> ls.logInfo(player.toString() + '\n'));
                }
                case 3 -> getTop3s();
                case 4 -> {
                    List<String[]> list = getFixture();
                    ls.logInfo(list.size()+ "");
//                    list.forEach(strings -> Arrays.stream(strings).forEach(ls::logInfo));
                    try {
                        writeToCSV(list);
                    } catch (IOException e) {
                        ls.logWarn("Didnt able to write in csv");
                        return;
                    }
                    ls.logInfo("written to csv");
                    return;
                }

                case 5 ->ls.logInfo("Exiting Crypto Menu. Goodbye!");
                default -> ls.logWarn("Not a valid task");
            }
        }while (choice != 5);
    }

    public static void getTop3s(){
        List<Player> playerInContentionForRuns = new ArrayList<>();
        List<Player> playerInContentionForWickets = new ArrayList<>();

        teams.forEach((key,value) -> {
            playerInContentionForRuns.addAll(value.topNRunScorer(3));
            playerInContentionForWickets.addAll(value.getTopNWicketTaker(3));
        });
        ls.logInfo("Top Scores of this season : ");
        playerInContentionForRuns.stream().sorted(new Comparator<Player>() {
            @Override
            public int compare(Player player, Player t1) {
                return Integer.compare(t1.getRuns(),player.getRuns());
            }
        }).limit(3).toList().forEach(player -> ls.logInfo(player.toString() + '\n'));
        ls.logInfo("Top wicket taker of this season : ");
        playerInContentionForWickets.stream().sorted(new Comparator<Player>() {
            @Override
            public int compare(Player player, Player t1) {
                return Integer.compare(t1.getWickets(),player.getWickets());
            }
        }).limit(3).toList().forEach(player -> ls.logInfo(player.toString() + '\n'));
    }


    public static void writeToCSV(List<String[]> data) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("/home/hp/code/backend/java fundarmentals/Assesment1/src/main/resources/fixtures.csv"));
        String line1[] = {"Date", "Match number", "Team home", "Team away", "Ground"};
        writer.writeNext(line1);
        for(String[] line : data) {
            writer.writeNext(line);
        }
        writer.flush();
    }

    //Date Match number Team home Teamaway Ground
    public static List<String[]> getFixture()
    {
        List<String[]> data = new ArrayList<>();
        int matchCount = 1;
        String dt = "2024-03-01";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Collection<Team> allTeams = teams.values();
        for(Team team : allTeams)
        {

            for(Team otherTeam : allTeams)
            {
                if(team.getName().equals(otherTeam.getName())) continue;
                data.add(new String[]
                        {dt,matchCount + "",team.getName(),otherTeam.getName(),team.getHome()});
                matchCount++;
                c.add(Calendar.DATE, 1);  // number of days to add
                dt = sdf.format(c.getTime());
            }
        }
        return data;
    }

}
