package com.kdu.springmvc.service;

import com.kdu.springmvc.dto.SpeakerDTO;
import com.kdu.springmvc.dto.TyreDTO;
import com.kdu.springmvc.dto.request.VehicleRequestDTO;
import com.kdu.springmvc.dto.response.VehicleResponseDTO;
import com.kdu.springmvc.entity.Vehicle;
import com.kdu.springmvc.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.kdu.springmvc.exception.*;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to vehicles.
 * This class provides methods for CRUD operations on vehicles and additional functionalities.
 */
@Service
@Profile("dev")
@Slf4j
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    /**
     * Constructor for VehicleService, injecting the VehicleRepository dependency.
     *
     * @param vehicleRepository An instance of VehicleRepository for database operations.
     */
    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Creates a new vehicle.
     *
     * @param vehicleRequestDTO The request body containing information for creating a new vehicle.
     * @throws VehicleAlreadyExist Exception thrown if a vehicle with the same ID already exists.
     */
    public void create(VehicleRequestDTO vehicleRequestDTO) {
        Integer id = vehicleRequestDTO.getId();
        if (vehicleRepository.isPresent(id)) {
            throw new VehicleAlreadyExist(id);
        }
        Vehicle newVehicle = MapperService.toEntity(vehicleRequestDTO);
        vehicleRepository.save(newVehicle);
        log.info("New Vehicle Created with id " + id);
    }

    /**
     * Retrieves a vehicle by its ID.
     *
     * @param id The ID of the vehicle to retrieve.
     * @return VehicleResponseDTO containing information about the retrieved vehicle.
     * @throws VehicleNotFoundException Exception thrown if the requested vehicle is not found.
     */
    public VehicleResponseDTO findById(Integer id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            log.info("Vehicle retrieved with id " + id);
            return MapperService.toDTO(vehicle.get());
        } else {
            throw new VehicleNotFoundException(id);
        }
    }

    /**
     * Updates an existing vehicle by its ID.
     *
     * @param id                 The ID of the vehicle to update.
     * @param vehicleRequestDTO The request body containing updated information for the vehicle.
     * @throws VehicleNotFoundException Exception thrown if the requested vehicle is not found.
     */
    public void update(Integer id, VehicleRequestDTO vehicleRequestDTO) {
        if (vehicleRepository.isPresent(id)) {
            vehicleRepository.delete(id);
            Vehicle newVehicle = MapperService.toEntity(vehicleRequestDTO);
            vehicleRepository.save(newVehicle);
            log.info("Vehicle updated with id " + id);
        } else {
            log.warn("Nothing to update");
            throw new VehicleNotFoundException(id);
        }
    }

    /**
     * Deletes a vehicle by its ID.
     *
     * @param id The ID of the vehicle to delete.
     * @throws VehicleNotFoundException Exception thrown if the requested vehicle is not found.
     */
    public void delete(Integer id) {
        if (vehicleRepository.isPresent(id)) {
            vehicleRepository.delete(id);
            log.info("Vehicle deleted with id " + id);
        } else {
            log.warn("Nothing to delete");
            throw new VehicleNotFoundException(id);
        }
    }

    /**
     * Retrieves the vehicle with the maximum price.
     *
     * @return VehicleResponseDTO containing information about the vehicle with the maximum price.
     */
    public VehicleResponseDTO getMaxPricedVehicle() {
        Optional<Vehicle> vehicle = vehicleRepository.getMaxPricedVehicle();
        if (vehicle.isPresent()) {
            log.info("Max vehicle retrieved with id " + vehicle.get().getId());
            return MapperService.toDTO(vehicle.get());
        }
        log.info("Couldn't retrieve max vehicle");
        return getNullObj();
    }

    /**
     * Retrieves the vehicle with the minimum price.
     *
     * @return VehicleResponseDTO containing information about the vehicle with the minimum price.
     */
    public VehicleResponseDTO getMinPricedVehicle() {
        Optional<Vehicle> vehicle = vehicleRepository.getMinPricedVehicle();
        if (vehicle.isPresent()) {
            log.info("Min vehicle retrieved with id " + vehicle.get().getId());
            return MapperService.toDTO(vehicle.get());
        }
        log.info("Couldn't retrieve min vehicle");
        return getNullObj();
    }

    /**
     * Retrieves all vehicles.
     *
     * @return List of VehicleResponseDTOs containing information about all vehicles.
     */
    public List<VehicleResponseDTO> getAll() {
        log.info("Retrieving all vehicles....");
        return vehicleRepository.findAll()
                .stream()
                .map(MapperService::toDTO)
                .toList();
    }

    /**
     * Creates and returns a null object for VehicleResponseDTO.
     *
     * @return VehicleResponseDTO representing a null object.
     */
    public VehicleResponseDTO getNullObj() {
        return new VehicleResponseDTO.Builder().id(0).name("Null Object").price(0.0)
                .speaker(new SpeakerDTO("NULL", 0.0)).tyre(new TyreDTO("NULL", 0.0)).build();
    }
}
