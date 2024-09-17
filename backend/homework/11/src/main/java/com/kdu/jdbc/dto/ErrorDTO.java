package com.kdu.jdbc.dto;
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
                "\n    message='" + message + '\'' +
                ",\n    statusCode=" + statusCode +
                '\n' +
                '}';
    }
}
