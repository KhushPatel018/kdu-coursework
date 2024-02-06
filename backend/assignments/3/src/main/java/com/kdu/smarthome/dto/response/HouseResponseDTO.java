package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.entity.House;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseResponseDTO {
    private String message;
    private House house;
    private HttpStatus httpStatus;
}
