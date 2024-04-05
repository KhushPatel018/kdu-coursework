package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class InventoryResponseDTO {
    private String inventory;
    private HttpStatus httpStatus;
}
