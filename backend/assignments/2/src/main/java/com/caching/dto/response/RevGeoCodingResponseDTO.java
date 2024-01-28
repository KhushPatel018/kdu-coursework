package com.caching.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class RevGeoCodingResponseDTO {
    String address;
    @Setter
    String responseMessage;

    @Override
    public String toString() {
        return "RevGeoCodingResponseDTO{" +
                "address='" + address + '\'' +
                '}';
    }
}
