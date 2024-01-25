package com.kdu.springmvc.service;


import com.kdu.springmvc.dto.SpeakerDTO;
import com.kdu.springmvc.dto.TyreDTO;
import com.kdu.springmvc.dto.request.VehicleRequestDTO;
import com.kdu.springmvc.dto.response.VehicleResponseDTO;
import com.kdu.springmvc.entity.Speaker;
import com.kdu.springmvc.entity.Tyre;
import com.kdu.springmvc.entity.Vehicle;
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
