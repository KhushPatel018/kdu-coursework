package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddRoomRequestDTO;
import com.kdu.smarthome.dto.response.RoomResponseDTO;
import com.kdu.smarthome.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for room-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private final RoomService roomService;

    /**
     * Constructor for RoomController.
     *
     * @param roomService The service for room-related operations.
     */
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Endpoint for adding a room to a house.
     *
     * @param authorization         The authorization header.
     * @param houseId               The ID of the house to which the room will be added.
     * @param addRoomRequestDTO     The DTO containing information about the room to be added.
     * @return                      ResponseEntity containing the response DTO.
     */
    @PostMapping
    public ResponseEntity<RoomResponseDTO> addRoom(@Valid @RequestHeader String authorization,
                                                   @Valid @RequestParam String houseId,
                                                   @Valid @RequestBody AddRoomRequestDTO addRoomRequestDTO) {
        return ResponseEntity.ok(roomService.addRoomToHouse(houseId, addRoomRequestDTO, authorization));
    }
}
