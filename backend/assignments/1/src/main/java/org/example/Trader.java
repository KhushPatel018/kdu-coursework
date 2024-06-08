package org.example;

import java.util.HashMap;
import java.util.Map;

public class Trader{
    public static final LoggingSystem ls = new LoggingSystem();
    private int id;
    private String firstName;
    private String lastName;
    private String walletAddress;
    private String phone;
    private Map<String, Long> coinToVolume = new HashMap<>();

    public Map<String, Long> getCoinToVolume() {
        return coinToVolume;
    }

    public void setCoinToVolume(Map<String, Long> coinToVolume) {
        this.coinToVolume = coinToVolume;
    }

    public double getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(double profitLoss) {
        this.profitLoss = profitLoss;
    }

    private double profitLoss;

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public  String getFirstName() {
        return firstName;
    }

    public  void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public  String getLastName() {
        return lastName;
    }

    public  void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public  String getWalletAddress() {
        return walletAddress;
    }

    public  void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public  String getPhone() {
        return phone;
    }

    public  void setPhone(String phone) {
        this.phone = phone;
    }

    public  void printPortfolio() {
        ls.logInfo("Net profit-loss: " + profitLoss);
        coinToVolume.forEach((symbol, volume) -> ls.logInfo(symbol + " " + volume));
    }
    public  void buyCoin(String symbol,Long volume,double price) {
        // update portfolio
        Long totalQuantity = coinToVolume.getOrDefault(symbol,0L);
        coinToVolume.put(symbol,totalQuantity + volume);
        profitLoss -= volume*price;
    }

    public  void sellCoin(String symbol, Long volume, double price) {
        Long totalQuantity = coinToVolume.getOrDefault(symbol,0L);
        if(volume > totalQuantity){
           ls.logInfo("not enough " + symbol  + " to sell");
        }
        coinToVolume.put(symbol,totalQuantity - volume);
        profitLoss += volume*price;
    }

    public String toString(){

        return "id : " + id +", name : " + firstName + " " + lastName + ", phone : " + phone + ", address  : " + walletAddress;
    }

}
