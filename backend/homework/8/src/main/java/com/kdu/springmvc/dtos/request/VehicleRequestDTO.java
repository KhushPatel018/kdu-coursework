package com.kdu.springmvc.dtos.request;

import com.kdu.springmvc.dtos.SpeakerDTO;
import com.kdu.springmvc.dtos.TyreDTO;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

public class VehicleRequestDTO {
    @NumberFormat
    private Integer id;
    @NotBlank
    private String name;
    private SpeakerDTO speaker;
    private TyreDTO tyre;
    private double price;

    private VehicleRequestDTO() {
        // Private constructor to force the use of the builder
    }

    public VehicleRequestDTO(Integer id, String name, SpeakerDTO speaker, TyreDTO tyre, double price) {
        this.id = id;
        this.name = name;
        this.speaker = speaker;
        this.tyre = tyre;
        this.price = price;
    }

    public static class Builder {
        private final VehicleRequestDTO vehicleRequestDTO;

        public Builder() {
            this.vehicleRequestDTO = new VehicleRequestDTO();
        }

        public Builder id(Integer id) {
            vehicleRequestDTO.id = id;
            return this;
        }

        public Builder name(String name) {
            vehicleRequestDTO.name = name;
            return this;
        }

        public Builder speaker(SpeakerDTO speaker) {
            vehicleRequestDTO.speaker = speaker;
            return this;
        }

        public Builder tyre(TyreDTO tyre) {
            vehicleRequestDTO.tyre = tyre;
            return this;
        }

        public Builder price(double price) {
            vehicleRequestDTO.price = price;
            return this;
        }

        public VehicleRequestDTO build() {
            return vehicleRequestDTO;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SpeakerDTO getSpeaker() {
        return speaker;
    }

    public TyreDTO getTyre() {
        return tyre;
    }

    public double getPrice() {
        return price;
    }
}
