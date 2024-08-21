package com.kdu.springmvc.repository;

import com.kdu.springmvc.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for performing CRUD operations on the 'Vehicle' entity.
 */
@Repository
public class VehicleRepository {

    private final List<Vehicle> vehicles = new ArrayList<>();

    /**
     * Saves a new vehicle.
     *
     * @param vehicle The Vehicle entity to be saved.
     */
    public void save(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    /**
     * Retrieves all vehicles.
     *
     * @return A list of all vehicles.
     */
    public List<Vehicle> findAll() {
        return this.vehicles;
    }

    /**
     * Retrieves a vehicle by its ID.
     *
     * @param id The ID of the vehicle to retrieve.
     * @return An Optional containing the Vehicle entity, if found.
     */
    public Optional<Vehicle> findById(Integer id) {
        return vehicles.stream().filter(vehicle -> vehicle.getId().equals(id)).findFirst();
    }

    /**
     * Checks if a vehicle with the given ID exists.
     *
     * @param id The ID of the vehicle to check for existence.
     * @return true if the vehicle with the given ID exists; false otherwise.
     */
    public boolean isPresent(Integer id) {
        return vehicles.stream().anyMatch(vehicle -> vehicle.getId().equals(id));
    }

    /**
     * Deletes a vehicle by its ID.
     *
     * @param id The ID of the vehicle to delete.
     */
    public void delete(Integer id) {
        vehicles.removeIf(vehicle1 -> vehicle1.getId().equals(id));
    }

    /**
     * Retrieves the vehicle with the maximum price.
     *
     * @return An Optional containing the Vehicle entity with the maximum price.
     */
    public Optional<Vehicle> getMaxPricedVehicle() {
        return vehicles.stream()
                .reduce((vehicle, vehicle2) -> vehicle.getPrice() > vehicle2.getPrice() ? vehicle : vehicle2);
    }

    /**
     * Retrieves the vehicle with the minimum price.
     *
     * @return An Optional containing the Vehicle entity with the minimum price.
     */
    public Optional<Vehicle> getMinPricedVehicle() {
        return vehicles.stream()
                .reduce((vehicle, vehicle2) -> vehicle.getPrice() > vehicle2.getPrice() ? vehicle2 : vehicle);
    }
}
