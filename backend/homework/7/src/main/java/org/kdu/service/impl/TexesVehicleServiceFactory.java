package org.kdu.service.impl;

import jakarta.annotation.PostConstruct;
import org.kdu.constansts.LoggingSystem;
import org.kdu.entites.Speaker;
import org.kdu.entites.Tyre;
import org.kdu.entites.Vehicle;
import org.kdu.repository.VehicleInventory;
import org.kdu.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class TexesVehicleServiceFactory implements IVehicleService {

    Random random = new Random();


    String location;

    // injected automatically
    TyreService tyreService;

    SpeakerService speakerService;

    public VehicleInventory getInventory() {
        return inventory;
    }

    // field injection
    @Autowired
    @Qualifier("texes")
    VehicleInventory inventory;

    // constructor injection
    @Autowired
    public TexesVehicleServiceFactory(TyreService tyreService, SpeakerService speakerService) {
        this.tyreService = tyreService;
        this.speakerService = speakerService;
    }

    /**
     * it is @PostConstruct methode with will be called just after bean object creation
     */
    @PostConstruct
    public void initialize() {
        LoggingSystem.logInfo("Texes Service Generated");
    }

    /**
     * generates a vehicle
     *
     * @return Vehicle object with speaker and tyre
     */
    public Vehicle generateVehicles() {
        Speaker sonySpeaker = speakerService.getSpeakerOfSony();
        Speaker boseSpeaker = speakerService.getSpeakerBose();
        Tyre bridgestoneTyre = tyreService.getTyreOfBridgestone();
        Tyre mrfTyre = tyreService.getTyreOfMRF();


        Vehicle tesla = new Vehicle();
        tesla.setSpeaker(random.nextInt(2) == 1 ? sonySpeaker : boseSpeaker);
        tesla.setTyre(random.nextInt(2) == 1 ? bridgestoneTyre : mrfTyre);
        double vehiclePrice = sonySpeaker.getPrice() + bridgestoneTyre.getPrice() + random.nextDouble(10000);
        tesla.setPrice(vehiclePrice * inventory.getHike());
        return tesla;
    }

    @Override
    public void setLocation() {
        this.location = "Texes";
    }

    /**
     * @param n no of vehicles you want to generate
     */
    public void addNVehicles(int n) {
        for (int noOfVehicles = 0; noOfVehicles < n; noOfVehicles++) {
            inventory.getVehicles().add(generateVehicles());
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inventory.getVehicles().forEach(vehicle -> sb.append(vehicle.toString()).append("\n"));
        return sb.toString();
    }

}
