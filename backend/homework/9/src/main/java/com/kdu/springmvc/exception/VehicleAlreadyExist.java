package com.kdu.springmvc.exception;

public class VehicleAlreadyExist extends RuntimeException{
    final Integer id;
    public VehicleAlreadyExist(Integer id){
        super();
        this.id = id;
    }

    @Override
    public String toString() {
        return "VehicleAlreadyExist: {vehicle with id " + id + " already exists}";
    }
}


