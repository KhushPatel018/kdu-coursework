package com.kdu.jpa.exception;

public class ItemsNotFound extends RuntimeException{
    final String msg;

    public ItemsNotFound(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ItemsNotFound{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
