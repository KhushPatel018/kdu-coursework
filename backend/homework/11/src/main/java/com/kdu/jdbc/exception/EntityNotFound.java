package com.kdu.jdbc.exception;

public class EntityNotFound extends RuntimeException{
    final String msg;
    public EntityNotFound(String msg)
    {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "EntityNotFound{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
