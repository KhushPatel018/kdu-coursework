package com.caching.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder

public class GeoCodingResponseDTO {
    Double latitude;
    Double longitude;
    @Setter
    String responseMessage;

    @Override
    public String toString() {
        return "GeoCodingResponseDTO{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
