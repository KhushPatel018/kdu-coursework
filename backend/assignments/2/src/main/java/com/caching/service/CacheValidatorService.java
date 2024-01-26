package com.caching.service;

import com.caching.entity.Location;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Service for validating caching conditions.
 */
@Service
public class CacheValidatorService {

    /**
     * The restricted place used for caching validation.
     */
    public static final String PLACE = "goa";

    /**
     * Checks if the provided location is restricted for caching.
     *
     * @param location The location to check for caching restriction.
     * @return True if the location is restricted, false otherwise.
     */
    public boolean checkForRestrictedCaching(Location location) {
        if (checkNull(location.getAddress()) && location.getAddress().toLowerCase().contains(PLACE)) return true;
        if (checkNull(location.getCity()) && location.getCity().toLowerCase().contains(PLACE)) return true;
        if (checkNull(location.getState()) && location.getState().toLowerCase().contains(PLACE)) return true;
        return checkNull(location.getCountry()) && location.getCountry().toLowerCase().contains(PLACE);
    }

    /**
     * Checks if the provided object is null.
     *
     * @param obj The object to check for null.
     * @return True if the object is not null, false otherwise.
     */
    public boolean checkNull(Object obj) {
        return !(Objects.isNull(obj));
    }

    /**
     * Validates latitude and longitude for caching.
     *
     * @param latitude  The latitude to validate.
     * @param longitude The longitude to validate.
     * @return True if both latitude and longitude are not null, false otherwise.
     */
    public boolean validateLatLon(String latitude, String longitude) {
        return !(checkNull(latitude) || checkNull(longitude));
    }

    /**
     * Validates address for caching.
     *
     * @param address The address to validate.
     * @return True if the address is not null, false otherwise.
     */
    public boolean validateAddress(String address) {
        return !checkNull(address);
    }
}
