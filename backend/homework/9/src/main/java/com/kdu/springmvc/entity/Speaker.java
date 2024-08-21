package com.kdu.springmvc.entity;


public class Speaker {
    String brand;
    double price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Speaker(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }
}
