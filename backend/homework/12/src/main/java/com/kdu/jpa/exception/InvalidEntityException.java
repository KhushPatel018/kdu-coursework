package com.kdu.jpa.exception;

public class InvalidEntityException extends RuntimeException{
    final String msg;
    final String entity;

    public InvalidEntityException(String msg, String entity) {
        this.msg = msg;
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "InvalidEntityException{" +
                "msg='" + msg + '\'' +
                ", entity='" + entity + '\'' +
                '}';
    }
}
