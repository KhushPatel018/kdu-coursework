package com.caching.exception;

public class AddressNotFound extends RuntimeException{
    final String lat;
    final String lon;
    public AddressNotFound(String lat,String lon){
        super();
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "AddressNotFound for lat = " + lat + " & lon = " + lon;
    }
}
