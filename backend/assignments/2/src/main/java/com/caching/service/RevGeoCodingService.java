package com.caching.service;

import com.caching.dto.response.RevGeoCodingResponseDTO;
import com.caching.entity.Location;
import com.caching.exception.AddressNotFound;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Service for reverse geocoding operations.
 */
@Service
@Slf4j
public class RevGeoCodingService {
    private final ThirdPartyApiService thirdPartyApiService;
    private final CacheValidatorService cacheValidatorService;

    /**
     * Constructor for RevGeoCodingService.
     *
     * @param thirdPartyApiService   The third-party API service.
     * @param cacheValidatorService  The cache validator service.
     */
    @Autowired
    public RevGeoCodingService(ThirdPartyApiService thirdPartyApiService, CacheValidatorService cacheValidatorService) {
        this.thirdPartyApiService = thirdPartyApiService;
        this.cacheValidatorService = cacheValidatorService;
    }

    /**
     * Retrieves reverse geocoding information based on latitude and longitude.
     *
     * @param latitude  The latitude of the location.
     * @param longitude The longitude of the location.
     * @return RevGeoCodingResponseDTO representing the reverse geocoding information.
     * @throws AddressNotFound If the address is not found in the response.
     */
    @Cacheable(value = "reverse-geocoding", key = "{#latitude,#longitude}", cacheManager = "caffeineCacheManager", unless = "#result.getResponseMessage().equals(\"!cached\")")
    public RevGeoCodingResponseDTO getRevGeocoding(String latitude, String longitude) {
        JsonNode data = thirdPartyApiService.getDataForLatitudeLongitude(latitude, longitude);
        Location location = MapperService.toEntity(data);
        if (cacheValidatorService.validateAddress(location.getAddress())) {
            log.error("Can't find address");
            throw new AddressNotFound(latitude, longitude);
        }
        RevGeoCodingResponseDTO result = MapperService.reverseGeoCodingResponseDTO(location);
        log.info("Location successfully retrieved for lat = {} & lon = {} from API", latitude, longitude);
        if (cacheValidatorService.checkForRestrictedCaching(location)) {
            result.setResponseMessage("!cached");
        } else result.setResponseMessage("cached");
        return result;
    }
}
