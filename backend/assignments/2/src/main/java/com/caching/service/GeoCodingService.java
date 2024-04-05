package com.caching.service;

import com.caching.dto.response.GeoCodingResponseDTO;
import com.caching.entity.Location;
import com.caching.exception.LatitudeLongitudeNotFound;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Service for geocoding operations.
 */
@Slf4j
@Service
public class GeoCodingService {

    private final ThirdPartyApiService thirdPartyApiService;
    private final CacheValidatorService cacheValidatorService;

    /**
     * Constructor for GeoCodingService.
     *
     * @param thirdPartyApiService   The third-party API service.
     * @param cacheValidatorService  The cache validator service.
     */
    @Autowired
    public GeoCodingService(ThirdPartyApiService thirdPartyApiService, CacheValidatorService cacheValidatorService) {
        this.thirdPartyApiService = thirdPartyApiService;
        this.cacheValidatorService = cacheValidatorService;
    }

    /**
     * Retrieves geocoding information based on the provided address.
     *
     * @param address The address for which to retrieve geocoding information.
     * @return GeoCodingResponseDTO representing the geocoding information.
     * @throws LatitudeLongitudeNotFound If latitude or longitude is not found in the response.
     */
    @Cacheable(value = "geocoding", key = "#address", cacheManager = "caffeineCacheManager", unless = "#result.getResponseMessage().equals(\"!cached\")")
    public GeoCodingResponseDTO getGeocoding(String address) {
        JsonNode data = thirdPartyApiService.getDataForLocation(address);
        Location location = MapperService.toEntity(data);

        if (cacheValidatorService.validateLatLon(location.getLatitude(), location.getLongitude())) {
            log.error("Can't find latitude - longitude");
            throw new LatitudeLongitudeNotFound(address);
        }

        log.info("Longitude-Latitude successfully retrieved for {} from API", location.getAddress());
        GeoCodingResponseDTO result = MapperService.geoCodingResponseDTO(location);

        if (cacheValidatorService.checkForRestrictedCaching(location)) {
            result.setResponseMessage("!cached");
        } else result.setResponseMessage("cached");

        return result;
    }
}
