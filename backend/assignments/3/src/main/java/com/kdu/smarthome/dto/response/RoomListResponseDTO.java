package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomListResponseDTO {
    private String message;
    private String roomsAndDevices;
    private HttpStatus httpStatus;

}
