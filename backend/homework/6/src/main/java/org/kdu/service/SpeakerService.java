package org.kdu.service;

import org.kdu.Constants;
import org.kdu.entites.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SpeakerService {
    @Bean
    Speaker getSpeakerOfSony(){
        return new Speaker(Constants.speakerBrand.SONY,1000.0);
    }

    @Bean Speaker getSpeakerBose(){
        return new Speaker(Constants.speakerBrand.BOSE,900.0);
    }


}
