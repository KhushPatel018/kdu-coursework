package com.caching.controller;

import com.caching.dto.response.GeoCodingResponseDTO;
import com.caching.service.GeoCodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling GeoCoding related endpoints.
 */
@RestController
@RequestMapping("/geocoding")
public class GeoCodingController {

    private final GeoCodingService service;

    /**
     * Constructor to initialize the controller with a GeoCodingService.
     *
     * @param service GeoCodingService instance.
     */
    @Autowired
    public GeoCodingController(GeoCodingService service) {
        this.service = service;
    }

    /**
     * Endpoint to perform GeoCoding based on the provided address.
     *
     * @param address The address for GeoCoding.
     * @return ResponseEntity with the GeoCodingResponseDTO and HTTP status.
     */
    @GetMapping
    public ResponseEntity<GeoCodingResponseDTO> getGeocoding(@RequestParam String address) {
        return new ResponseEntity<>(service.getGeocoding(address), HttpStatus.OK);
    }
}
