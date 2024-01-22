package org.kdu.entites;

import org.kdu.Constants;


public class Tyre {
    private Constants.tyreBrand brand;
    private double price;

    public Constants.tyreBrand getBrand() {
        return brand;
    }

    public void setBrand(Constants.tyreBrand brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Tyre(Constants.tyreBrand brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "brand = " + brand +
                ", price = " + price +
                '}';
    }
}
