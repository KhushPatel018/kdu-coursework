package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddDeviceRequestDTO;
import com.kdu.smarthome.dto.request.RegisterDeviceRequestDTO;
import com.kdu.smarthome.dto.response.GlobalResponseDTO;
import com.kdu.smarthome.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
    @PostMapping("/register")
    public ResponseEntity<GlobalResponseDTO> registerDevice(@Valid @RequestHeader String authorization,
                                                            @Valid @RequestBody RegisterDeviceRequestDTO
                                                                    deviceRegisterRequestDTO) {
        return ResponseEntity.ok(deviceService.registerDevice(deviceRegisterRequestDTO, authorization));
    }
    @PostMapping("/add")
    public ResponseEntity<GlobalResponseDTO> addDeviceToHouse(@Valid @RequestHeader String authorization,
                                                        @Valid @RequestBody AddDeviceRequestDTO addDeviceRequestDTO) {
        return ResponseEntity.ok(deviceService.addDeviceToHouse(addDeviceRequestDTO, authorization));
    }
}
