package org.kdu.service;

import org.kdu.Constants;
import org.kdu.entites.Tyre;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class TyreService {
    @Bean(name = "bridgestone")
    Tyre getTyreOfBridgestone(){
        return new Tyre(Constants.tyreBrand.BRIDGESTONE,1000.0);
    }
    @Bean(name = "mrf")
    Tyre getTyreOfMRF(){
        return new Tyre(Constants.tyreBrand.MRF,1100.0);
    }
}
