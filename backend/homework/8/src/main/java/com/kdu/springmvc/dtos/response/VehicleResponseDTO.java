package com.kdu.springmvc.dtos.response;

import com.kdu.springmvc.dtos.SpeakerDTO;
import com.kdu.springmvc.dtos.TyreDTO;

public class VehicleResponseDTO {
    private Integer id;
    private String name;
    private SpeakerDTO speaker;
    private TyreDTO tyre;
    private double price;

    private VehicleResponseDTO() {
        // Private constructor to force the use of the builder
    }

    public static class Builder {
        private final VehicleResponseDTO vehicleResponseDTO;

        public Builder() {
            this.vehicleResponseDTO = new VehicleResponseDTO();
        }

        public Builder id(Integer id) {
            vehicleResponseDTO.id = id;
            return this;
        }

        public Builder name(String name) {
            vehicleResponseDTO.name = name;
            return this;
        }

        public Builder speaker(SpeakerDTO speaker) {
            vehicleResponseDTO.speaker = speaker;
            return this;
        }

        public Builder tyre(TyreDTO tyre) {
            vehicleResponseDTO.tyre = tyre;
            return this;
        }

        public Builder price(double price) {
            vehicleResponseDTO.price = price;
            return this;
        }

        public VehicleResponseDTO build() {
            return vehicleResponseDTO;
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

    @Override
    public String toString() {
        return "VehicleResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speaker=" + speaker.toString() +
                ", tyre=" + tyre.toString() +
                ", price=" + price +
                '}';
    }
}
