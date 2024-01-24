package com.kdu.springmvc.services;


import com.kdu.springmvc.dtos.SpeakerDTO;
import com.kdu.springmvc.dtos.TyreDTO;
import com.kdu.springmvc.dtos.request.VehicleRequestDTO;
import com.kdu.springmvc.dtos.response.VehicleResponseDTO;
import com.kdu.springmvc.entities.Speaker;
import com.kdu.springmvc.entities.Tyre;
import com.kdu.springmvc.entities.Vehicle;
import org.springframework.stereotype.Service;


@Service
public class MapperService {

    private MapperService() {

    }

    public static Vehicle toEntity(VehicleRequestDTO vehicleRequestDTO) {
        return new Vehicle.VehicleBuilder()
                .id(vehicleRequestDTO.getId())
                .name(vehicleRequestDTO.getName())
                .speaker(new Speaker(vehicleRequestDTO.getSpeaker().getBrand(), vehicleRequestDTO.getSpeaker().getPrice()))
                .tyre(new Tyre(vehicleRequestDTO.getTyre().getBrand(), vehicleRequestDTO.getTyre().getPrice()))
                .price(vehicleRequestDTO.getPrice())
                .build();
    }

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
