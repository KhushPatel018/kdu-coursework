package com.caching.entity;

import lombok.*;

@Builder
@Getter
public class Location {
    String address;
    String country;
    String state;
    String city;
    String latitude;
    String longitude;
    String postcode;

    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                '}';
    }
}
