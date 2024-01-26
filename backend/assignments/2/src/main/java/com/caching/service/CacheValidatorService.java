package com.caching.service;

import com.caching.entity.Location;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Service for validating caching conditions.
 */
@Service
public class CacheValidatorService {

    /**
     * The restricted place used for caching validation.
     */
    List<String> restrictedPlaces = new ArrayList<>(Arrays.asList("goa","mumbai"));

    /**
     * @param place place to add to the list
     */
    public void addRestrictedCachingPlace(String place){
        restrictedPlaces.add(place.toLowerCase());
    }

    /**
     * @param place all places related to this will be removed
     */
    public void removeRestrictedCachingPlace(String place)
    {
        restrictedPlaces.removeIf(item -> place.toLowerCase().equals(item));
    }
    /**
     * Checks if the provided location is restricted for caching.
     *
     * @param location The location to check for caching restriction.
     * @return True if the location is restricted, false otherwise.
     */
    public boolean checkForRestrictedCaching(Location location) {
        for(String place : restrictedPlaces)
        {
            if(checkForRestrictedCachingItem(location,place))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * operates on single item
     */
    public boolean checkForRestrictedCachingItem(Location location,String item)
    {
        if (checkNull(location.getAddress()) && location.getAddress().toLowerCase().contains(item)) return true;
        if (checkNull(location.getCity()) && location.getCity().toLowerCase().contains(item)) return true;
        if (checkNull(location.getState()) && location.getState().toLowerCase().contains(item)) return true;
        return checkNull(location.getCountry()) && location.getCountry().toLowerCase().contains(item);
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
