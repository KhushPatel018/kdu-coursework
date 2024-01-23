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
public class AustinVehicleServiceFactory implements IVehicleService {


    public VehicleInventory getInventory() {
        return inventory;
    }

    VehicleInventory inventory;
    Random random = new Random();
    String location;

    TyreService tyreService;

    SpeakerService speakerService;


    // setter injection
    @Autowired
    @Qualifier("austin")
    public void setInventory(VehicleInventory inventory) {
        this.inventory = inventory;
    }


    // constructor injection
    @Autowired
    public AustinVehicleServiceFactory(TyreService tyreService, SpeakerService speakerService) {
        this.tyreService = tyreService;
        this.speakerService = speakerService;
    }

    /**
     * it is @PostConstruct methode with will be called just after bean object creation
     */
    @PostConstruct
    public void initialize() {
        LoggingSystem.logInfo("Austin Service Generated");
    }

    @Override
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
        this.location = "Austin";
    }

    @Override
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
