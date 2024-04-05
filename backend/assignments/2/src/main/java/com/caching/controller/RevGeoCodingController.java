package com.caching.controller;

import com.caching.dto.response.RevGeoCodingResponseDTO;
import com.caching.service.RevGeoCodingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling Reverse GeoCoding related endpoints.
 */
@RestController
@RequestMapping("/reverse-geocoding")
@Slf4j
public class RevGeoCodingController {

    private final RevGeoCodingService service;

    /**
     * Constructor to initialize the controller with a RevGeoCodingService.
     *
     * @param service RevGeoCodingService instance.
     */
    @Autowired
    public RevGeoCodingController(RevGeoCodingService service) {
        this.service = service;
    }

    /**
     * Endpoint to perform Reverse GeoCoding based on the provided latitude and longitude.
     *
     * @param latitude  The latitude for Reverse GeoCoding.
     * @param longitude The longitude for Reverse GeoCoding.
     * @return ResponseEntity with the RevGeoCodingResponseDTO and HTTP status.
     */
    @GetMapping
    public ResponseEntity<RevGeoCodingResponseDTO> getRevGeoCoding(@RequestParam String latitude, @RequestParam String longitude) {
        return new ResponseEntity<>(service.getRevGeocoding(latitude, longitude), HttpStatus.OK);
    }
}
