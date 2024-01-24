package com.kdu.springmvc.services;


import com.kdu.springmvc.constants.LoggingSystem;
import com.kdu.springmvc.dtos.SpeakerDTO;
import com.kdu.springmvc.dtos.TyreDTO;
import com.kdu.springmvc.dtos.request.VehicleRequestDTO;
import com.kdu.springmvc.dtos.response.VehicleResponseDTO;
import com.kdu.springmvc.entities.Vehicle;
import com.kdu.springmvc.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kdu.springmvc.exceptions.*;

import java.util.List;
import java.util.Optional;


@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public void create(VehicleRequestDTO vehicleRequestDTO) throws VehicleAlreadyExist {
        Integer id = vehicleRequestDTO.getId();
        if (vehicleRepository.isPresent(id)) {
            throw new VehicleAlreadyExist(id);
        }
        Vehicle newVehicle = MapperService.toEntity(vehicleRequestDTO);
        vehicleRepository.save(newVehicle);
    }

    public VehicleResponseDTO findById(Integer id) throws VehicleNotFoundException {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            return MapperService.toDTO(vehicle.get());
        } else throw new VehicleNotFoundException(id);

    }

    public void update(Integer id, VehicleRequestDTO vehicleRequestDTO) throws VehicleNotFoundException {
        if (vehicleRepository.isPresent(id)) {
            vehicleRepository.delete(id);
            Vehicle newVehicle = MapperService.toEntity(vehicleRequestDTO);
            vehicleRepository.save(newVehicle);
        } else {
            LoggingSystem.logInfo("Nothing to update");
            throw new VehicleNotFoundException(id);
        }
    }

    public void delete(Integer id) throws VehicleNotFoundException {
        if (vehicleRepository.isPresent(id)) {
            vehicleRepository.delete(id);
        } else {
            LoggingSystem.logInfo("Nothing To delete");
            throw new VehicleNotFoundException(id);
        }
    }

    public VehicleResponseDTO getMaxPricedVehicle() {
        Optional<Vehicle> vehicle = vehicleRepository.getMaxPricedVehicle();
        if (vehicle.isPresent()) {
            return MapperService.toDTO(vehicle.get());
        }
        return getNullObj();
    }

    public VehicleResponseDTO getMinPricedVehicle() {
        Optional<Vehicle> vehicle = vehicleRepository.getMinPricedVehicle();
        if (vehicle.isPresent()) {
            return MapperService.toDTO(vehicle.get());
        }
        return getNullObj();
    }

    public List<VehicleResponseDTO> getAll() {
        return vehicleRepository.findAll()
                .stream()
                .map(MapperService::toDTO)
                .toList();
    }

    public VehicleResponseDTO getNullObj() {
        return new VehicleResponseDTO.Builder().id(0).name("Null Object").price(0.0).speaker(new SpeakerDTO("NULL", 0.0)).tyre(new TyreDTO("NULL", 0.0)).build();
    }
}

