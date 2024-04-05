package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor@Builder
@NoArgsConstructor
public class RoomResponseDTO {
    private String message;
    private Room room;
    private HttpStatus httpStatus;
}
