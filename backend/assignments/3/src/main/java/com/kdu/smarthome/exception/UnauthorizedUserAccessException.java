package com.kdu.smarthome.exception;

public class UnauthorizedUserAccessException extends RuntimeException{
    public UnauthorizedUserAccessException(String message) {
        super(message);
    }
}
