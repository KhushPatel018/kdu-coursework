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

/**
 * Controller class for house-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/house")
public class HouseController {
    private final HouseService houseService;

    /**
     * Constructor for HouseController.
     *
     * @param houseService The service for house-related operations.
     */
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * Endpoint for retrieving all houses.
     *
     * @return ResponseEntity containing the response DTO.
     */
    @GetMapping
    public ResponseEntity<HouseListResponseDTO> getAllHouses() {
        return ResponseEntity.ok(houseService.getAllHouses());
    }

    /**
     * Endpoint for retrieving details of a specific house.
     *
     * @param houseId The ID of the house.
     * @return        ResponseEntity containing the response DTO.
     */
    @GetMapping("/{houseId}")
    public ResponseEntity<RoomListResponseDTO> getHouseDetails(@Valid @PathVariable("houseId") String houseId) {
        return ResponseEntity.ok(houseService.getHouseDetails(houseId));
    }

    /**
     * Endpoint for adding a new house.
     *
     * @param authorization             The authorization token.
     * @param registerHouseRequestDTO   The DTO containing information about the house to be added.
     * @return                          ResponseEntity containing the response DTO.
     */
    @PostMapping
    public ResponseEntity<HouseResponseDTO> addHouse(@Valid @RequestHeader String authorization,
                                                     @Valid @RequestBody RegisterHouseRequestDTO registerHouseRequestDTO) {
        return ResponseEntity.ok(houseService.addHouse(registerHouseRequestDTO, authorization));
    }

    /**
     * Endpoint for adding a user to a house.
     *
     * @param authorization             The authorization token.
     * @param houseId                   The ID of the house.
     * @param addUserToHouseRequestDTO  The DTO containing information about the user to be added.
     * @return                          ResponseEntity containing the response DTO.
     * @throws JsonProcessingException  If there is an issue processing the JSON.
     */
    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<GlobalResponseDTO> addUserToHouse(@Valid @RequestHeader String authorization,
                                                            @Valid @PathVariable String houseId,
                                                            @Valid @RequestBody AddUserRequestDTO addUserToHouseRequestDTO)
            throws JsonProcessingException {
        return ResponseEntity.ok(houseService.addUserToHouse(houseId, addUserToHouseRequestDTO, authorization));
    }

    /**
     * Endpoint for updating the address of a house.
     *
     * @param authorization The authorization token.
     * @param houseId       The ID of the house.
     * @param houseDTO      The DTO containing the updated address information.
     * @return              ResponseEntity containing the response DTO.
     */
    @PutMapping
    public ResponseEntity<GlobalResponseDTO> updateHouseAddress(@Valid @RequestHeader String authorization,
                                                                @Valid @RequestParam String houseId,
                                                                @Valid @RequestBody UpdateAddressRequestDTO houseDTO) {
        return ResponseEntity.ok(houseService.updateHouseAddress(houseId, houseDTO, authorization));
    }
}
