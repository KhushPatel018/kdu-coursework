package org.kdu.service.impl;

import org.kdu.constansts.Constants;
import org.kdu.entites.Tyre;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TyreService {
    @Bean(name = "bridgestone")
    Tyre getTyreOfBridgestone() {
        Tyre tyre = new Tyre();
        tyre.setBrand(Constants.tyreBrand.BRIDGESTONE);
        tyre.setPrice(1000.0);
        return tyre;
    }

    @Bean(name = "mrf")
    Tyre getTyreOfMRF() {
        Tyre tyre = new Tyre();
        tyre.setBrand(Constants.tyreBrand.MRF);
        tyre.setPrice(1100.0);
        return tyre;
    }
}
