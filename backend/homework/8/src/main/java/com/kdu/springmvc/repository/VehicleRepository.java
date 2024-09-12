package com.kdu.springmvc.repository;
import com.kdu.springmvc.entities.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepository {
    private final List<Vehicle> vehicles = new ArrayList<>();

    public void save(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public List<Vehicle> findAll() {
        return this.vehicles;
    }

    public Optional<Vehicle> findById(Integer id) {
        return vehicles.stream().filter(vehicle -> vehicle.getId().equals(id)).findFirst();
    }

    public boolean isPresent(Integer id) {
        return vehicles.stream().anyMatch(vehicle -> vehicle.getId().equals(id));
    }

    public void delete(Integer id) {
        vehicles.removeIf(vehicle1 -> vehicle1.getId().equals(id));
    }

    /**
     * logs maximum priced vehicle
     */

    public Optional<Vehicle> getMaxPricedVehicle() {

        return vehicles.stream()
                .reduce((vehicle, vehicle2) -> vehicle.getPrice() > vehicle2.getPrice() ? vehicle : vehicle2);
    }

    /**
     * logs minimum priced vehicle
     */

    public Optional<Vehicle> getMinPricedVehicle() {

        return vehicles.stream()
                .reduce((vehicle, vehicle2) -> vehicle.getPrice() > vehicle2.getPrice() ? vehicle2 : vehicle);
    }
}

