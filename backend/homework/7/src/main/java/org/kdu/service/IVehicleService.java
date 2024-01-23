package org.kdu.service;

import org.kdu.entites.Vehicle;

public interface IVehicleService {
    public Vehicle generateVehicles();
    public void setLocation();

    public void addNVehicles(int n);

}
