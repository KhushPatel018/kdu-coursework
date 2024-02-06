package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddDeviceRequestDTO;
import com.kdu.smarthome.dto.request.RegisterDeviceRequestDTO;
import com.kdu.smarthome.dto.response.GlobalResponseDTO;
import com.kdu.smarthome.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for device-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {
    private final DeviceService deviceService;

    /**
     * Constructor for DeviceController.
     *
     * @param deviceService The service for device-related operations.
     */
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * Endpoint for registering a device.
     *
     * @param authorization             The authorization token.
     * @param deviceRegisterRequestDTO  The DTO containing information about the device to be registered.
     * @return                          ResponseEntity containing the response DTO.
     */
    @PostMapping("/register")
    public ResponseEntity<GlobalResponseDTO> registerDevice(@Valid @RequestHeader String authorization,
                                                            @Valid @RequestBody RegisterDeviceRequestDTO deviceRegisterRequestDTO) {
        return ResponseEntity.ok(deviceService.registerDevice(deviceRegisterRequestDTO, authorization));
    }

    /**
     * Endpoint for adding a device to a house.
     *
     * @param authorization         The authorization token.
     * @param addDeviceRequestDTO   The DTO containing information about the device to be added.
     * @return                      ResponseEntity containing the response DTO.
     */
    @PostMapping("/add")
    public ResponseEntity<GlobalResponseDTO> addDeviceToHouse(@Valid @RequestHeader String authorization,
                                                              @Valid @RequestBody AddDeviceRequestDTO addDeviceRequestDTO) {
        return ResponseEntity.ok(deviceService.addDeviceToHouse(addDeviceRequestDTO, authorization));
    }
}
