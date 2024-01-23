package org.kdu.repository;


import org.kdu.constansts.LoggingSystem;
import org.kdu.entites.Vehicle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
@Scope("prototype")
public class VehicleInventory {
    private List<Vehicle> vehicles;

    public double getHike() {
        return hike;
    }

    public void setHike(double hike) {
        this.hike = hike;
    }

    private double hike;
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * logs maximum priced vehicle
     */

    public void getMaxPricedVehicle() {

        vehicles.stream()
                .sorted(((vehicle, t1) -> t1.compareTo(vehicle)))
                .limit(1)
                .toList()
                .forEach(vehicle -> LoggingSystem.logInfo("Costliest Vehicle : " + vehicle.toString()));
    }

    /**
     * logs minimum priced vehicle
     */

    public void getMinPricedVehicle() {

        vehicles.stream()
                .sorted((Vehicle::compareTo))
                .limit(1)
                .toList()
                .forEach(vehicle -> LoggingSystem.logInfo("Cheapest Vehicle : " + vehicle.toString()));
    }
}
