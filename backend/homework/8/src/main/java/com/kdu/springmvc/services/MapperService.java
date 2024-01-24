package com.kdu.springmvc.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public static final String PRICE = "price";
    public static VehicleRequestDTO toVehicleRequestDTO(JsonNode vehicleJson) {
        JsonNode speakerData = vehicleJson.get("speaker");
        JsonNode tyreData = vehicleJson.get("tyre");
        Integer id = Integer.parseInt(vehicleJson.get("id").asText());
        String name = vehicleJson.get("name").asText();
        double price = vehicleJson.get(PRICE).asDouble();
        SpeakerDTO speakerDTO = new SpeakerDTO(speakerData.get("brand").asText(), speakerData.get(PRICE).asDouble());
        TyreDTO tyreDTO = new TyreDTO(tyreData.get("brand").asText(), tyreData.get(PRICE).asDouble());
        return new VehicleRequestDTO.Builder().id(id).name(name).price(price).speaker(speakerDTO).tyre(tyreDTO).build();
    }

    public static VehicleRequestDTO mapPayload(String jsonPayload) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonPayload);
        return toVehicleRequestDTO(jsonNode);
    }
}
