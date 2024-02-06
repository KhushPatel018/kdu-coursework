package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddRoomRequestDTO;
import com.kdu.smarthome.dto.response.RoomResponseDTO;
import com.kdu.smarthome.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponseDTO> addRoom(@Valid @RequestHeader String authorization,
                                                   @Valid @RequestParam String houseId,
                                                   @Valid @RequestBody AddRoomRequestDTO addRoomRequestDTO) {
        return ResponseEntity.ok(roomService.addRoomToHouse(houseId, addRoomRequestDTO, authorization));
    }

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
}
