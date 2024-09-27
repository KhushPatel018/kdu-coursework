package org.kdu;


import org.kdu.config.ProjectConfig;
import org.kdu.constansts.LoggingSystem;
import org.kdu.service.impl.AustinVehicleServiceFactory;
import org.kdu.service.impl.TexesVehicleServiceFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class Main {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        TexesVehicleServiceFactory texesVehicleServiceFactory = context.getBean(TexesVehicleServiceFactory.class);
        AustinVehicleServiceFactory austinVehicleServiceFactory = context.getBean(AustinVehicleServiceFactory.class);
        LoggingSystem.logInfo("Give the number of vehicles you want to add to the inventories : ");
        int n = sc.nextInt();
        texesVehicleServiceFactory.addNVehicles(n);
        austinVehicleServiceFactory.addNVehicles(n);
        LoggingSystem.logInfo("Generated Vehicles in texes : ");
        LoggingSystem.logInfo(texesVehicleServiceFactory.toString());
        texesVehicleServiceFactory.getInventory().getMaxPricedVehicle();
        texesVehicleServiceFactory.getInventory().getMinPricedVehicle();
        LoggingSystem.logInfo("\n");
        LoggingSystem.logInfo("Generated Vehicles in austin : ");
        LoggingSystem.logInfo(austinVehicleServiceFactory.toString());
        austinVehicleServiceFactory.getInventory().getMaxPricedVehicle();
        austinVehicleServiceFactory.getInventory().getMinPricedVehicle();
    }
}