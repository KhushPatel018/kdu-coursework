package org.kdu;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class TeamRepository {
    private static final LoggingSystem ls = new LoggingSystem();
    private HashMap<String, Team> teams = new HashMap<>();

    public Map<String, Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team, String name) {
        if (team == null) {
            ls.logWarn("Team is null");
            return;
        }
        if (name == null) {
            ls.logWarn("name is null");
            return;
        }
        teams.put(name, team);
    }


    public void loadCsv(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> r = reader.readAll();
            for (int i = 1; i < r.size(); i++) {
                String[] data = r.get(i);
                String name = data[0];
                String team = data[1];
                String role = data[2];
                String matches = data[3];
                String runs = data[4];
                String average = data[5];
                String sr = data[6];
                String wickets = data[7];
                Team newTeam;
                if (teams.get(team) == null) {
                    newTeam = new Team(team, new ArrayList<>(), team + "_home");
                    addTeam(newTeam, team);

                } else {
                    newTeam = teams.get(team);
                }
                Player player = new Player(name, newTeam, role, Integer.parseInt(matches.trim()), Integer.parseInt(runs.trim()), Integer.parseInt(wickets.trim()), Double.parseDouble(sr.trim()), Double.parseDouble(average));
                newTeam.getPlayers().add(player);
            }

        } catch (IOException | CsvException e) {
            ls.logInfo(e.getMessage());
        }
        ls.logInfo("Teams : " + teams.size());
    }

    public List<String[]> getFixture() {
        List<String[]> data = new ArrayList<>();
        int matchCount = 1;
        String dt = "2024-03-01";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            ls.logInfo("Couldn't Parse");
        }

        Collection<Team> allTeams = teams.values();
        for (Team team : allTeams) {

            for (Team otherTeam : allTeams) {
                if (team.getName().equals(otherTeam.getName())) continue;
                data.add(new String[]
                        {dt + "7 : 30 PM", matchCount + "", team.getName(), otherTeam.getName(), team.getHome()});
                matchCount++;
                c.add(Calendar.DATE, 1);  // number of days to add
                dt = sdf.format(c.getTime());
            }
        }
        return data;
    }

    public void writeToCSV(List<String[]> data) {
        CSVWriter writer = null;
        FileWriter fileWriter = null;
        try {
                fileWriter = new FileWriter(Constants.FIXTURE_FILE);
                writer = new CSVWriter(fileWriter);
                String[] line1 = {"Date", "Match number", "Team home", "Team away", "Ground"};
                writer.writeNext(line1);
                writer.writeAll(data);
                writer.flush();
        } catch (IOException e) {
            ls.logError("I/O Error while writing CSV");
        }
        finally {

                if(writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        ls.logError(e.toString());
                    }
                }

                if (fileWriter != null) {

                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        ls.logError(e.toString());
                    }
                }
            }

        }

    public void getTop3s() {
        List<Player> playerInContentionForRuns = new ArrayList<>();
        List<Player> playerInContentionForWickets = new ArrayList<>();

        getTeams().forEach((key, value) -> {
            playerInContentionForRuns.addAll(value.topNRunScorer(3));
            playerInContentionForWickets.addAll(value.getTopNWicketTaker(3));
        });
        ls.logInfo("Top Scores of this season : ");
        playerInContentionForRuns.stream().sorted((player, t1) -> Integer.compare(t1.getRuns(), player.getRuns())).limit(3).toList().forEach(player -> ls.logInfo(player.toString() + '\n'));
        ls.logInfo("Top wicket taker of this season : ");
        playerInContentionForWickets.stream().sorted((player, t1) -> Integer.compare(t1.getWickets(), player.getWickets())).limit(3).toList().forEach(player -> ls.logInfo(player.toString() + '\n'));
    }
    }

