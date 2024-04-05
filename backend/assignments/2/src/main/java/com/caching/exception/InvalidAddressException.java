package com.caching.exception;

public class InvalidAddressException extends RuntimeException{
    final String address;

    public InvalidAddressException(String address) {
        super();
        this.address = address;
    }

    @Override
    public String toString() {
        return "InvalidAddressException for" +
                "address = '" + address + '\'';
    }
}