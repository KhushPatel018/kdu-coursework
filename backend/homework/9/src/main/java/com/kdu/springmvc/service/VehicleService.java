package com.kdu.springmvc.service;


import com.kdu.springmvc.dto.SpeakerDTO;
import com.kdu.springmvc.dto.TyreDTO;
import com.kdu.springmvc.dto.request.VehicleRequestDTO;
import com.kdu.springmvc.dto.response.VehicleResponseDTO;
import com.kdu.springmvc.entity.Vehicle;
import com.kdu.springmvc.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.kdu.springmvc.exception.*;

import java.util.List;
import java.util.Optional;


@Service
@Profile("prod")
@Slf4j
public class VehicleService {

    private final VehicleRepository vehicleRepository;


    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public void create(VehicleRequestDTO vehicleRequestDTO){
        Integer id = vehicleRequestDTO.getId();
        if (vehicleRepository.isPresent(id)) {
            throw new VehicleAlreadyExist(id);
        }
        Vehicle newVehicle = MapperService.toEntity(vehicleRequestDTO);
        vehicleRepository.save(newVehicle);
        log.info("New Vehicle Created with id " + id);

    }

    public VehicleResponseDTO findById(Integer id)  {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            log.info("vehicle retrieved with id " + id);
            return MapperService.toDTO(vehicle.get());
        } else throw new VehicleNotFoundException(id);

    }

    public void update(Integer id, VehicleRequestDTO vehicleRequestDTO){
        if (vehicleRepository.isPresent(id)) {
            vehicleRepository.delete(id);
            Vehicle newVehicle = MapperService.toEntity(vehicleRequestDTO);
            vehicleRepository.save(newVehicle);
            log.info("vehicle updated with id " + id);
        } else {
            log.warn("Nothing to update");
            throw new VehicleNotFoundException(id);
        }
    }

    public void delete(Integer id) {
        if (vehicleRepository.isPresent(id)) {
            vehicleRepository.delete(id);
            log.info("vehicle deleted with id " + id);
        } else {
            log.warn("Nothing To delete");
            throw new VehicleNotFoundException(id);
        }
    }

    public VehicleResponseDTO getMaxPricedVehicle() {
        Optional<Vehicle> vehicle = vehicleRepository.getMaxPricedVehicle();
        if (vehicle.isPresent()) {
            log.info("max vehicle retrieved with id " + vehicle.get().getId());
            return MapperService.toDTO(vehicle.get());
        }
        log.info("couldn't retrieved max vehicle");
        return getNullObj();
    }

    public VehicleResponseDTO getMinPricedVehicle() {
        Optional<Vehicle> vehicle = vehicleRepository.getMinPricedVehicle();
        if (vehicle.isPresent()) {
            log.info("min vehicle retrieved with id " + vehicle.get().getId());
            return MapperService.toDTO(vehicle.get());
        }
        log.info("couldn't retrieved min vehicle");
        return getNullObj();
    }

    public List<VehicleResponseDTO> getAll() {
        log.info("retrieving all vehicles....");
        return vehicleRepository.findAll()
                .stream()
                .map(MapperService::toDTO)
                .toList();
    }

    public VehicleResponseDTO getNullObj() {
        return new VehicleResponseDTO.Builder().id(0).name("Null Object").price(0.0).speaker(new SpeakerDTO("NULL", 0.0)).tyre(new TyreDTO("NULL", 0.0)).build();
    }
}

