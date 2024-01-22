package org.kdu.entites;

import org.kdu.Constants;



public class Speaker {
    private Constants.speakerBrand brand;
    private double price;

    public Constants.speakerBrand getBrand() {
        return brand;
    }

    public void setBrand(Constants.speakerBrand brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Speaker(Constants.speakerBrand brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "brand =" + brand +
                ", price =" + price +
                '}';
    }
}
