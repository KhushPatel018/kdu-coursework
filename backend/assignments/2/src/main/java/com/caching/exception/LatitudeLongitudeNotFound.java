package com.caching.exception;

public class LatitudeLongitudeNotFound extends RuntimeException{
    final String address;

    public LatitudeLongitudeNotFound(String address) {
        super();
        this.address = address;
    }

    @Override
    public String toString() {
        return "LatitudeLongitudeNotFound for" +
                "address = '" + address + '\'';
    }
}
