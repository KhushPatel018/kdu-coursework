package org.kdu.service;

import jakarta.annotation.PostConstruct;
import org.kdu.LoggingSystem;
import org.kdu.entites.Speaker;
import org.kdu.entites.Tyre;
import org.kdu.entites.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class VehicleService {
    public static final LoggingSystem ls = new LoggingSystem();
    List<Vehicle> vehicles;
    Random random = new Random();
    @Autowired
    TyreService tyreService;
    @Autowired
    SpeakerService speakerService;

    /**
     * it is @PostConstruct methode with will be called just after bean object creation
     */
    @PostConstruct
    public void initialize(){
        vehicles = new ArrayList<>();
        ls.logInfo("Service Generated");
    }

    /**
     * generates a vehicle
     * @return Vehicle object with speaker and tyre
     */
    public Vehicle generateVehicles(){
        Speaker sonySpeaker = speakerService.getSpeakerOfSony();
        Speaker boseSpeaker = speakerService.getSpeakerBose();
        Tyre bridgestoneTyre = tyreService.getTyreOfBridgestone();
        Tyre mrfTyre = tyreService.getTyreOfMRF();


        Vehicle tesla = new Vehicle();
        tesla.setSpeaker(random.nextInt(2) == 1 ? sonySpeaker : boseSpeaker);
        tesla.setTyre(random.nextInt(2) == 1 ? bridgestoneTyre : mrfTyre);
        tesla.setPrice(sonySpeaker.getPrice() + bridgestoneTyre.getPrice() + random.nextDouble(10000));
        return tesla;
    }

    /**
     * @param n no of vehicles you want to generate
     */
    public void addNVehicles(int n){
        for(int noOfVehicles = 0; noOfVehicles < n;noOfVehicles++){
            vehicles.add(generateVehicles());
        }
    }

    /**
     * logs maximum priced object
     */
    public void getMaxPricedVehicle(){

        vehicles.stream()
                .sorted(((vehicle, t1) -> t1.compareTo(vehicle)))
                .limit(1)
                .toList()
                .forEach(vehicle -> ls.logInfo("Costliest Vehicle : " + vehicle.toString()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        vehicles.forEach(vehicle -> sb.append(vehicle.toString()).append("\n"));
        return sb.toString();
    }

}
