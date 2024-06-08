package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
public class TraderList {
    public static final HashMap<String, Trader> traders = new HashMap<>();
    private static TraderList accessTraders = new TraderList();
    public static final LoggingSystem ls = new LoggingSystem();


    private TraderList(){

    }
    public static TraderList getAccessTraders() {
        if(accessTraders == null){
            accessTraders = new TraderList();
        }
        return accessTraders;
    }

    public static void parse(String filePath){
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> r = reader.readAll();
            for (int i = 1; i < r.size(); i++) {
                String[] traderDetails = r.get(i);
                Trader trader = new Trader();
                int id = Integer.parseInt(traderDetails[0]);
                trader.setId(id);
                 String firstName = traderDetails[1];
                 trader.setFirstName(firstName);
                 String lastName = traderDetails[2];
                 trader.setLastName(lastName);
                 String walletAddress = traderDetails[4];
                 trader.setWalletAddress(walletAddress);
                 String phone = traderDetails[3];
                trader.setPhone(phone);
                traders.put(walletAddress,trader);
            }
            ls.logInfo("Traders Loaded.....");
        } catch (IOException | CsvException e) {
            ls.logInfo(e.getMessage());
        }
    }
    public static synchronized Trader getTrader(String address) {
        Trader trader = traders.get(address);
        if (trader == null) {
            ls.logInfo("trader doesn't exist with address" + address);
        }
        return trader;
    }

    public static void printTopNTraders(int n){
        List<Trader> traderCollection = traders.values().stream().sorted((trader, t1) -> Double.compare(t1.getProfitLoss(),trader.getProfitLoss())).limit(n).toList();

        traderCollection.forEach(trader -> ls.logInfo(trader.toString()));
    }

    public static void printLastNTraders(int n){
        List<Trader> traderCollection = traders.values().stream().sorted((trader, t1) -> Double.compare(trader.getProfitLoss(),t1.getProfitLoss())).limit(n).toList();

        traderCollection.forEach(trader -> ls.logInfo(trader.toString()));
    }
}
