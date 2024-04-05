package com.caching.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service for interacting with a third-party geocoding API.
 */
@Slf4j
@Service
public class ThirdPartyApiService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${geoapify.api-key}")
    private String apiKey;

    /**
     * Retrieves geocoding information based on the provided location.
     *
     * @param location The location for which to retrieve geocoding information.
     * @return JsonNode representing the geocoding information.
     */
    public JsonNode getDataForLocation(String location) {
        // Build the URL with the location parameter
        String apiUrl = "https://api.geoapify.com/v1/geocode/search";
        String encodedLocation = UriComponentsBuilder.fromUriString(location).build().toUriString();
        String url = apiUrl + "?text=" + encodedLocation + "&apiKey=" + apiKey;

        // Make a request to the third-party API
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

        // Process the response as needed
        JsonNode responseData = response.getBody();
        log.info("Geo API response: ");
        log.info(responseData.toString());
        return responseData;
    }

    /**
     * Retrieves reverse geocoding information based on latitude and longitude.
     *
     * @param latitude  The latitude of the location.
     * @param longitude The longitude of the location.
     * @return JsonNode representing the reverse geocoding information.
     */
    public JsonNode getDataForLatitudeLongitude(String latitude, String longitude) {
        String apiUrl = "https://api.geoapify.com/v1/geocode/reverse";
        String url = apiUrl + "?lat=" + latitude + "&lon=" + longitude + "&apiKey=" + apiKey;

        // Make a request to the third-party API
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

        // Process the response as needed
        JsonNode responseData = response.getBody();
        log.info("Rev-Geo API response: ");
        log.info(responseData.toString());
        return responseData;
    }
}
