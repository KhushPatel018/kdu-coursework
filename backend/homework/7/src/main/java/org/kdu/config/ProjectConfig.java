package org.kdu.config;

import org.kdu.repository.VehicleInventory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
@ComponentScan(basePackages = "org.kdu.service")
public class ProjectConfig {
    @Bean("texes")
    VehicleInventory getTexesInventory() {
        VehicleInventory vehicleInventory = new VehicleInventory();
        vehicleInventory.setVehicles(new ArrayList<>());
        vehicleInventory.setHike(1.10);
        return vehicleInventory;
    }

    @Bean("austin")
    VehicleInventory getAustinInventory() {
        VehicleInventory vehicleInventory = new VehicleInventory();
        vehicleInventory.setVehicles(new ArrayList<>());
        vehicleInventory.setHike(1.05);
        return vehicleInventory;
    }
}
