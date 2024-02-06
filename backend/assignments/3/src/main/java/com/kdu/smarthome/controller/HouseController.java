package com.kdu.smarthome.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.request.AddUserRequestDTO;
import com.kdu.smarthome.dto.request.RegisterHouseRequestDTO;
import com.kdu.smarthome.dto.request.UpdateAddressRequestDTO;
import com.kdu.smarthome.dto.response.GlobalResponseDTO;
import com.kdu.smarthome.dto.response.HouseListResponseDTO;
import com.kdu.smarthome.dto.response.HouseResponseDTO;
import com.kdu.smarthome.dto.response.RoomListResponseDTO;
import com.kdu.smarthome.service.HouseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/house")
public class HouseController {
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping
    public ResponseEntity<HouseListResponseDTO> getAllHouses() {
        return ResponseEntity.ok(houseService.getAllHouses());
    }

    @GetMapping("/{houseId}")
    public ResponseEntity<RoomListResponseDTO> getHouseDetails(@Valid @PathVariable("houseId") String houseId) {
        return ResponseEntity.ok(houseService.getHouseDetails(houseId));
    }

    @PostMapping
    public ResponseEntity<HouseResponseDTO> addHouse(@Valid @RequestHeader String authorization,
                                                     @Valid @RequestBody RegisterHouseRequestDTO registerHouseRequestDTO) {
        return ResponseEntity.ok(houseService.addHouse(registerHouseRequestDTO, authorization));
    }

    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<GlobalResponseDTO> addUserToHouse(@Valid @RequestHeader String authorization,
                                                            @Valid @PathVariable String houseId,
                                                            @Valid @RequestBody AddUserRequestDTO addUserToHouseRequestDTO)
            throws JsonProcessingException {
        return ResponseEntity.ok(houseService.addUserToHouse(houseId, addUserToHouseRequestDTO, authorization));
    }

    @PutMapping
    public ResponseEntity<GlobalResponseDTO> updateHouseAddress(@Valid @RequestHeader String authorization,
                                                                @Valid @RequestParam String houseId,
                                                                @Valid @RequestBody UpdateAddressRequestDTO houseDTO) {
        return ResponseEntity.ok(houseService.updateHouseAddress(houseId, houseDTO, authorization));
    }

}
