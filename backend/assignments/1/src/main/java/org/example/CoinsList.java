package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CoinsList {
    public static HashMap<String, Coins> coins = new HashMap<>();
    private static CoinsList accessCoins = new CoinsList();
    public static final LoggingSystem ls = new LoggingSystem();

    private CoinsList(){

    }
    public static CoinsList getAccessCoins() {
        if(accessCoins == null){
            accessCoins = new CoinsList();
        }
        return accessCoins;
    }

    public static void parse(String filePath){
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> r = reader.readAll();
            for (int i = 1; i < r.size(); i++) {
                String[] coinsDetails = r.get(i);
                Coins coins1 = new Coins();
                coins1.setCoinName(coinsDetails[2]);
                coins1.setPrice(Double.parseDouble(coinsDetails[4]));
                coins1.setRank(Integer.parseInt(coinsDetails[1]));
                coins1.setSymbol(coinsDetails[3]);
                coins1.setCirculationSupply(Long.parseLong(coinsDetails[5]));
                coins.put(coins1.getSymbol(), coins1);
            }
            ls.logInfo("coins Loaded.....");
        } catch (IOException | CsvException e) {
            ls.logInfo(e.getMessage());
        }
    }
    public static synchronized Coins getCoins(String address) {
        Coins coin = coins.get(address);
        if (coin == null) {
            ls.logInfo("coins doesn't exist with address" + address);
        }
        return coin;
    }


    public static void printTopNcoins(int n){
        List<Coins> coinsCollection = coins.values().stream().sorted((coin, t1) -> Double.compare(t1.getPrice(),coin.getPrice())).limit(n).toList();

        coinsCollection.forEach(Coins::printCoinDetails);
    }
}

