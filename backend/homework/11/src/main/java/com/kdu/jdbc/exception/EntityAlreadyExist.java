package com.kdu.jdbc.exception;

public class EntityAlreadyExist extends RuntimeException{
    final String msg;
    public EntityAlreadyExist(String msg)
    {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "EntityAlreadyExist{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
