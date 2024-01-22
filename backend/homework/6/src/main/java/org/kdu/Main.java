package org.kdu;


import org.kdu.config.VehicleServiceConfig;
import org.kdu.service.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class Main {
    private static final LoggingSystem ls = new LoggingSystem();
    public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(VehicleServiceConfig.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);
        ls.logInfo("Give the number of vehicles you want to make : " );
        int n = sc.nextInt();
        vehicleService.addNVehicles(n);
        ls.logInfo("Generated Vehicles : ");
        ls.logInfo(vehicleService.toString());
        vehicleService.getMaxPricedVehicle();
    }
}