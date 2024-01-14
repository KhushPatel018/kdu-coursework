
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static final Scanner sc = new Scanner(System.in);
    public static final LoggingSystem ls = new LoggingSystem();
    static JsonNode jsonNode;
    static CountDownLatch countDownLatch;
    static ExecutorService executorService;

    public static  void executeTransactions(JsonNode jsonTransactions, CountDownLatch latch) {
        executorService = Executors.newFixedThreadPool(jsonTransactions.size());
        for (JsonNode jsonNode: jsonTransactions){
            executorService.execute(new ExecuteTransaction(jsonNode,latch));
        }
        try {
            latch.await();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            ls.logWarn(e.getMessage());
        }
        finally {
            executorService.shutdown();
        }
    }


    public static void main(String[] args){
        // initial coins and traders loaded
        String[] filePaths = {
                "/home/hp/code/backend/java fundarmentals/JavaAssignment1/src/main/resources/small_transaction.json",
                "/home/hp/code/backend/java fundarmentals/JavaAssignment1/src/main/resources/medium_transaction.json",
                "/home/hp/code/backend/java fundarmentals/JavaAssignment1/src/main/resources/large_transaction.json"
        };
        TraderList.parse("/home/hp/code/backend/java fundarmentals/JavaAssignment1/src/main/resources/traders.csv");
        CoinsList.parse("/home/hp/code/backend/java fundarmentals/JavaAssignment1/src/main/resources/coins.csv");
        String jsonFilePath = filePaths[0];
        parseJsonFile(jsonFilePath);
        countDownLatch = new CountDownLatch(jsonNode.size());
        Thread thread = new Thread(() -> executeTransactions(jsonNode,countDownLatch));
        thread.start();
        startApp();
        try {
            thread.join();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            ls.logWarn(e.getMessage());
        }
    }

    public static void startApp() {
        char option;
        do {
            ls.logInfo("Crypto Menu:");
            ls.logInfo("a. Given the name or code of a coin, retrieve all its details.");
            ls.logInfo("b. Display top N coins in the market based on price.");
            ls.logInfo("c. For a given trader, show his portfolio.");
            ls.logInfo("d. For a given trader, display the total profit or loss they have made trading in the crypto market.");
            ls.logInfo("e. Show top 5 and bottom 5 traders based on their profit/loss.");
            ls.logInfo("f. Exit");

            ls.logInfo("Enter your choice (a/b/c/d/e/f): ");
            option = sc.nextLine().charAt(0);


            switch (option) {

                case 'a':
                    ls.logInfo("Option a selected: Retrieve coin details");
                    ls.logInfo("Give Coin Code : ");
                    String code = sc.nextLine();
                    Coins coins = CoinsList.getCoins(code);
                    if (coins == null) {
                        ls.logInfo("No coin found");
                    } else {
                        coins.printCoinDetails();
                    }
                    break;
                case 'b':
                    ls.logInfo("Option b selected: Display top N coins");
                    int n = sc.nextInt();
                    sc.nextLine();
                    CoinsList.PrintTopNcoins(n);
                    break;
                case 'c':
                    ls.logInfo("Option c selected: Show trader portfolio");
                    ls.logInfo("Give me Traders walletAddress : ");
                    String walletAddress = sc.nextLine();
                    TraderList.getTrader(walletAddress).printPortfolio();
                    break;
                case 'd':
                    ls.logInfo("Option d selected: Display total profit/loss for a trader");
                    ls.logInfo("Give me Traders walletAddress : ");
                    walletAddress = sc.nextLine();
                    ls.logInfo("net profit-loss : " + TraderList.getTrader(walletAddress).getProfitLoss());
                    break;
                case 'e':
                    ls.logInfo("Option e selected: Show top 5 and bottom 5 traders");
                    ls.logInfo("Top five traders : ");
                    TraderList.PrintTopNTraders(5);
                    ls.logInfo("Bottom five traders : ");
                    TraderList.PrintLastNTraders(5);
                    break;
                case 'f':
                    // Exit the program
                    ls.logInfo("Exiting Crypto Menu. Goodbye!");
                    break;
                default:
                    ls.logInfo("Invalid choice. Please enter a valid option.");
            }

        } while (option != 'f');

        sc.close();
    }

    public static JsonNode parseJsonFile(String jsonString){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonNode = objectMapper.readTree(new File(jsonString));
        } catch (Exception e) {
            ls.logInfo(e.getMessage());
        }
        return jsonNode;
    }
}
