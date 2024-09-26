package org.kdu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.kdu.service")
public class VehicleServiceConfig {
}
