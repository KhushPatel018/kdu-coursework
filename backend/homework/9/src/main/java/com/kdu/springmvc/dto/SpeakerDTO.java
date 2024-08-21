package com.kdu.springmvc.dto;

public class SpeakerDTO {
    private String brand;
    private double price;

    public SpeakerDTO(String brand, double price) {
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
