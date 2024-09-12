package com.kdu.springmvc.exceptions;

public class VehicleNotFoundException extends Exception{

    final Integer id;
    public VehicleNotFoundException(Integer id){
        super();
        this.id = id;
    }

    @Override
    public String toString() {
        return "VehicleNotFoundException : {no vehicle with id : }" + id;
    }
}
