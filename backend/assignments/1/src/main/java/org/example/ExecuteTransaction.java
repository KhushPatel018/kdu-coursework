package org.example;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ExecuteTransaction implements Runnable {
    public static final LoggingSystem ls = new LoggingSystem();

    private JsonNode jsonTransaction;
    private CountDownLatch latch;
    public static final TraderList traders = TraderList.getAccessTraders();
    public static final CoinsList coins = CoinsList.getAccessCoins();
    Random rnd;

    String notCoin = " is not a coin";
    public ExecuteTransaction(){

    }
    public ExecuteTransaction(JsonNode transactions, CountDownLatch latch) {
        this.jsonTransaction = transactions;
        this.latch = latch;
    }

    @Override
    public synchronized void run() {

        try {
             getBlockHash();
            JsonNode data = jsonTransaction.get("data");
            if(data == null){
                ls.logInfo("data null");
            }
            String type =  jsonTransaction.get("type").asText();
            switch (type) {
                case "BUY" -> processBuyTransaction(data);
                case "SELL" -> processSellTransaction(data);
                case "ADD_VOLUME" -> addVolume(data);
                case "UPDATE_PRICE" -> updatePrice(data);
                default -> {
                    break;
                }
            }
        } finally {
            latch.countDown();
        }
    }

    private  void processBuyTransaction(JsonNode data) {
        String symbol = data.get("coin").asText();
        Coins coin = coins.getCoins(symbol);
        long quantity = data.get("quantity").asLong();
        String walletAddress = data.get("wallet_address").asText();
        Trader trader = traders.getTrader(walletAddress);

        if (trader == null){
           ls.logInfo("not a valid org.example.Trader");
           return;
        }

        long supply = coin.getCirculationSupply();
        double price  = coin.getPrice();

        synchronized (coin){
            while (quantity > supply){
                try{
                    // volume up or sell
                    coin.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                supply  = coin.getCirculationSupply();
            }
            // have enough to buy
            coin.setCirculationSupply(supply - quantity);
            trader.buyCoin(symbol,quantity,price);
            TraderList.traders.put(walletAddress,trader);
            CoinsList.coins.put(symbol,coin);
            // notifying coin buy was success
            coin.notifyAll();

        }
    }


    private  void processSellTransaction(JsonNode data) {
        String symbol = data.get("coin").asText();
        Coins coin = coins.getCoins(symbol);
        if(coin == null)
        {
            ls.logInfo(symbol + notCoin);
            return;
        }
        long quantity = data.get("quantity").asLong();
        String walletAddress = data.get("wallet_address").asText();
        Trader trader = traders.getTrader(walletAddress);

        if (trader == null){
            ls.logInfo("not a valid org.example.Trader");
            return;
        }

        long supply;
        double price  = coin.getPrice();
        synchronized (trader){
            supply = trader.getCoinToVolume().getOrDefault(symbol,0L);
        }

        synchronized (coin){
            while (quantity > supply){
                try{
                    // volume up or sell
                    trader.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                supply  = trader.getCoinToVolume().getOrDefault(symbol,0L);
            }
            // have enough to sell
            trader.sellCoin(symbol,quantity,price);
            coin.setCirculationSupply(coin.getCirculationSupply() + quantity);
            TraderList.traders.put(walletAddress,trader);
            CoinsList.coins.put(symbol,coin);
            // notifying trader's sell has been done
            trader.notifyAll();
        }
    }

    private void addVolume(JsonNode data) {

        String symbol = data.get("coin").asText();
        Coins coin = coins.getCoins(symbol);
        if(coin == null)
        {
            ls.logInfo(symbol + notCoin);
           return;
        }
        long volume = data.get("volume").asLong();
        synchronized (coin){
            coin.setCirculationSupply(coin.getCirculationSupply() + volume);
            CoinsList.coins.put(symbol,coin);
            coin.notifyAll();
        }
    }

    private void updatePrice(JsonNode data) {
        String symbol = data.get("coin").asText();
        Coins coin = ExecuteTransaction.coins.getCoins(symbol);
        if(coin == null)
        {
            ls.logInfo(symbol + notCoin);
            return;
        }
        double price = data.get("price").asDouble();
        synchronized (coin){
            coin.setPrice(price);
            CoinsList.coins.put(symbol,coin);
        }
    }

    private String getBlockHash() {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
         rnd = new Random();

        // Introducing delay mimicking complex calculation being performed.
        for (double i = 0; i < 199999999; i++) {
            i = i;
        }

        while (transactionHash.length() < 128) {
            int index = (int) (rnd.nextFloat() * saltChars.length());
            transactionHash.append(saltChars.charAt(index));
        }

        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }
}
