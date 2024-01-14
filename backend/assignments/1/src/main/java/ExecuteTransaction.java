import com.fasterxml.jackson.databind.JsonNode;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ExecuteTransaction implements Runnable {
    public static final LoggingSystem ls = new LoggingSystem();

    private JsonNode jsonTransaction;
    private CountDownLatch latch;
    public static TraderList traders = TraderList.getAccessTraders();
    public static CoinsList coins = CoinsList.getAccessCoins();
    public ExecuteTransaction(){

    }
    public ExecuteTransaction(JsonNode transactions, CountDownLatch latch) {
        this.jsonTransaction = transactions;
        this.latch = latch;
    }

    @Override
    public synchronized void run() {

        try {
            String hash = getBlockHash();
            JsonNode data = jsonTransaction.get("data");
            if(data == null){
                ls.logInfo("data null");
            }
            String type =  jsonTransaction.get("type").asText();
            switch (type) {
                case "BUY" -> processBuyTransaction(data,hash);
                case "SELL" -> processSellTransaction(data,hash);
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

    private  void processBuyTransaction(JsonNode data,String hash) {
        String symbol = data.get("coin").asText();
        Coins coin = coins.getCoins(symbol);
        long quantity = data.get("quantity").asLong();
        String walletAddress = data.get("wallet_address").asText();
        Trader trader = traders.getTrader(walletAddress);

        if (trader == null){
           ls.logInfo("not a valid Trader");
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

            // notifying coin buy was success
            coin.notifyAll();

        }
    }


    private  void processSellTransaction(JsonNode data,String hash) {
        String symbol = data.get("coin").asText();
        Coins coin = coins.getCoins(symbol);
        if(coin == null)
        {
            ls.logInfo(symbol + " is not a coin");
            return;
        }
        long quantity = data.get("quantity").asLong();
        String walletAddress = data.get("wallet_address").asText();
        Trader trader = traders.getTrader(walletAddress);

        if (trader == null){
            ls.logInfo("not a valid Trader");
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
                    coin.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                supply  = trader.getCoinToVolume().getOrDefault(symbol,0L);
            }
            // have enough to sell
            trader.sellCoin(symbol,quantity,price);
            coin.setCirculationSupply(coin.getCirculationSupply() + quantity);

            // notifying trader's sell has been done
            coin.notifyAll();
        }
    }

    private void addVolume(JsonNode data) {

        String symbol = data.get("coin").asText();
        Coins coin = coins.getCoins(symbol);
        if(coin == null)
        {
            ls.logInfo(symbol + " is not a coin");
           return;
        }
        long volume = data.get("volume").asLong();
        synchronized (coin){
            coin.setCirculationSupply(coin.getCirculationSupply() + volume);
            coin.notifyAll();
        }
    }

    private void updatePrice(JsonNode data) {
        String symbol = data.get("coin").asText();
        Coins coins = ExecuteTransaction.coins.getCoins(symbol);
        if(coins == null)
        {
            ls.logInfo(symbol + " is not a coin");
            return;
        }
        double price = data.get("price").asDouble();
        synchronized (coins){
            coins.setPrice(price);
        }
    }

    private String getBlockHash() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        Random rnd = new Random();

        // Introducing delay mimicking complex calculation being performed.
        for (double i = 0; i < 199999999; i++) {
            i = i;
        }

        while (transactionHash.length() < 128) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            transactionHash.append(SALTCHARS.charAt(index));
        }

        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }
}
