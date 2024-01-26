package com.caching.service;

import com.caching.dto.response.GeoCodingResponseDTO;
import com.caching.dto.response.RevGeoCodingResponseDTO;
import com.caching.entity.Location;
import com.caching.exception.InvalidAddressException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service responsible for mapping and transforming data between different representations.
 */
@Service
@Slf4j
public class MapperService {

    private MapperService() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts the JSON response from the third-party API to a Location entity.
     *
     * @param response The JSON response from the third-party API.
     * @return Location entity representing the geocoded information.
     * @throws InvalidAddressException If the address in the response is invalid.
     */
    public static Location toEntity(JsonNode response) {
        JsonNode features = response.get("features");

        if (features == null || features.isEmpty()) {
            String invalidAddress = response.get("query").get("text").asText();
            log.error("Invalid address entered: " + invalidAddress);
            throw new InvalidAddressException(invalidAddress);
        }

        JsonNode feature = features.get(0);
        JsonNode properties = feature.get("properties");

        String address = properties.get("formatted").asText();
        log.info("Formatted address: " + address);

        String state = getStringValue(properties, "state");
        String city = getStringValue(properties, "city");

        String latitude = (properties.get("lat") != null ? properties.get("lat").asText() : "null");
        log.info("Latitude: " + latitude);

        String longitude = (properties.get("lon") != null ? properties.get("lon").asText() : "null");
        log.info("Longitude: " + longitude);

        String postcode = getStringValue(properties, "postcode");
        String country = getStringValue(properties, "country");

        return Location.builder()
                .address(address)
                .longitude(longitude)
                .latitude(latitude)
                .state(state)
                .city(city)
                .postcode(postcode)
                .country(country)
                .build();
    }

    /**
     * Retrieves the String value of a field from a JsonNode.
     *
     * @param jsonNode  The JsonNode containing the field.
     * @param fieldName The name of the field to retrieve.
     * @return String value of the field or "null" if not present.
     */
    public static String getStringValue(JsonNode jsonNode, String fieldName) {
        JsonNode fieldNode = jsonNode.get(fieldName);
        return (fieldNode != null && !fieldNode.isNull()) ? fieldNode.asText() : "null";
    }

    /**
     * Converts a Location entity to a GeoCodingResponseDTO.
     *
     * @param location The Location entity to convert.
     * @return GeoCodingResponseDTO representing the geocoded information.
     */
    public static GeoCodingResponseDTO geoCodingResponseDTO(Location location) {
        return GeoCodingResponseDTO.builder()
                .latitude(Double.parseDouble(location.getLatitude()))
                .longitude(Double.parseDouble(location.getLongitude()))
                .build();
    }

    /**
     * Converts a Location entity to a RevGeoCodingResponseDTO.
     *
     * @param location The Location entity to convert.
     * @return RevGeoCodingResponseDTO representing the reverse geocoded information.
     */
    public static RevGeoCodingResponseDTO reverseGeoCodingResponseDTO(Location location) {
        return RevGeoCodingResponseDTO.builder().address(location.getAddress()).build();
    }
}
