package com.kdu.springmvc.dto.response;

public class ErrorDTO {
    String message;
    int statusCode;

    public ErrorDTO(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "Error{" +
                "message='" + message + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}
