package com.kdu.springmvc.entities;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public class Vehicle {

    @NumberFormat
    private Integer id;
    @NotBlank
    private String name;
    @NotNull
    private Speaker speaker;
    @NotNull
    private Tyre tyre;
    private double price;

    private Vehicle() {
        // Private constructor to force the use of the builder
    }

    public static class VehicleBuilder {

        private final Vehicle vehicle;

        public VehicleBuilder() {
            this.vehicle = new Vehicle();
        }

        public VehicleBuilder id(Integer id) {
            vehicle.id = id;
            return this;
        }

        public VehicleBuilder name(String name) {
            vehicle.name = name;
            return this;
        }

        public VehicleBuilder speaker(Speaker speaker) {
            vehicle.speaker = speaker;
            return this;
        }

        public VehicleBuilder tyre(Tyre tyre) {
            vehicle.tyre = tyre;
            return this;
        }

        public VehicleBuilder price(double price) {
            vehicle.price = price;
            return this;
        }

        public Vehicle build() {
            return vehicle;
        }
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Vehicle = {" +
                "speaker=" + speaker +
                ", tyre=" + tyre +
                ", price=" + price +
                '}';
    }

    public int compareTo(Vehicle vehicle) {
        return (int) (this.price - vehicle.price);
    }
}


