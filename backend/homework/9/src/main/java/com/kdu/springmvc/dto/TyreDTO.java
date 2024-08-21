package com.kdu.springmvc.dto;

public class TyreDTO {
    private String brand;
    private double price;


    public TyreDTO(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "{" +
                "\n    brand='" + brand + '\'' +
                ",\n    price=" + price +
                '\n' +
                '}';
    }
}
