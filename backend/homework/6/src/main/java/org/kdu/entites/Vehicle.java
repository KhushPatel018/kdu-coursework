package org.kdu.entites;


public class Vehicle {

    private Speaker speaker;

    private Tyre tyre;
    private double price;

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Vehicle = {" +
                "speaker=" + speaker.toString() +
                ", tyre=" + tyre.toString() +
                ", price=" + price +
                '}';
    }

    public int compareTo(Vehicle vehicle)
    {
        return (int)(this.price - vehicle.price);
    }
}
