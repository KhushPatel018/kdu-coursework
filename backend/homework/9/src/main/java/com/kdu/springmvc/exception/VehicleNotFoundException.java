package com.kdu.springmvc.exception;

public class VehicleNotFoundException extends RuntimeException{

    final Integer id;
    public VehicleNotFoundException(Integer id){
        super();
        this.id = id;
    }

    @Override
    public String toString() {
        return "VehicleNotFoundException: {no vehicle with id " + id + "}";
    }
}
