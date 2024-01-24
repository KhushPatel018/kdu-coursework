package com.kdu.springmvc.exceptions;

public class VehicleAlreadyExist extends Exception{
    final Integer id;
    public VehicleAlreadyExist(Integer id){
        super();
        this.id = id;
    }

    @Override
    public String toString() {
        return "VehicleAlreadyExist : {vehicle with id : }" + id + " already exist";
    }
}


