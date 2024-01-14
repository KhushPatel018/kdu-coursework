public class Coins {
    public static final LoggingSystem ls = new LoggingSystem();
    private int rank;
    private String name;
    private String symbol;
    private double price;
    private long circulationSupply;

    public  int getRank() {
        return rank;
    }

    public  void setRank(int rank) {
        this.rank = rank;
    }

    public  String getName() {
        return name;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public  String getSymbol() {
        return symbol;
    }

    public  void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    // sync updates in coin....
    public synchronized double getPrice() {
        return price;
    }

    public synchronized void setPrice(double price) {
        this.price = price;
    }

    public synchronized long getCirculationSupply() {
        return circulationSupply;
    }

    public void setCirculationSupply(long circulationSupply) {
        synchronized (this){
            this.circulationSupply = circulationSupply;
            notifyAll();
        }
    }

    // display sinc ?
    public void printCoinDetails() {
        ls.logInfo("Coin Details : ");
        ls.logInfo("Rank : " + rank);
        ls.logInfo("Symbol : " + symbol);
        ls.logInfo("Price : " + price);
        ls.logInfo("Circulation Supply : " + circulationSupply + "\n");
    }
}



