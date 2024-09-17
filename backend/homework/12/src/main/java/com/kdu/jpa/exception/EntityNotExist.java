package com.kdu.jpa.exception;

public class EntityNotExist extends RuntimeException{
    final String msg;
    final String entity;

    public EntityNotExist(String msg, String entity) {
        this.msg = msg;
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "EntityNotExist{" +
                "msg='" + msg + '\'' +
                ", entity='" + entity + '\'' +
                '}';
    }
}
