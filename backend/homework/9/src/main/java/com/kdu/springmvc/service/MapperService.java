package com.kdu.springmvc.service;

import com.kdu.springmvc.dto.SpeakerDTO;
import com.kdu.springmvc.dto.TyreDTO;
import com.kdu.springmvc.dto.request.VehicleRequestDTO;
import com.kdu.springmvc.dto.response.VehicleResponseDTO;
import com.kdu.springmvc.entity.Speaker;
import com.kdu.springmvc.entity.Tyre;
import com.kdu.springmvc.entity.Vehicle;
import org.springframework.stereotype.Service;

/**
 * Service class for mapping between DTOs (Data Transfer Objects) and entities.
 */
@Service
public class MapperService {

    /**
     * Private constructor to prevent instantiation since it's a utility class.
     */
    private MapperService() {
    }

    /**
     * Converts a VehicleRequestDTO to a Vehicle entity.
     *
     * @param vehicleRequestDTO The DTO containing information to be mapped to the entity.
     * @return Vehicle entity with information from the DTO.
     */
    public static Vehicle toEntity(VehicleRequestDTO vehicleRequestDTO) {
        return new Vehicle.VehicleBuilder()
                .id(vehicleRequestDTO.getId())
                .name(vehicleRequestDTO.getName())
                .speaker(new Speaker(vehicleRequestDTO.getSpeaker().getBrand(), vehicleRequestDTO.getSpeaker().getPrice()))
                .tyre(new Tyre(vehicleRequestDTO.getTyre().getBrand(), vehicleRequestDTO.getTyre().getPrice()))
                .price(vehicleRequestDTO.getPrice())
                .build();
    }

    /**
     * Converts a Vehicle entity to a VehicleResponseDTO.
     *
     * @param vehicle The entity containing information to be mapped to the DTO.
     * @return VehicleResponseDTO with information from the entity.
     */
    public static VehicleResponseDTO toDTO(Vehicle vehicle) {
        return new VehicleResponseDTO.Builder()
                .id(vehicle.getId())
                .name(vehicle.getName())
                .speaker(new SpeakerDTO(vehicle.getSpeaker().getBrand(), vehicle.getSpeaker().getPrice()))
                .tyre(new TyreDTO(vehicle.getSpeaker().getBrand(), vehicle.getPrice()))
                .price(vehicle.getPrice())
                .build();
    }
}
