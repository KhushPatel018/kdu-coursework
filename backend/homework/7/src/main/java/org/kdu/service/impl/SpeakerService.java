package org.kdu.service.impl;

import org.kdu.constansts.Constants;
import org.kdu.entites.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpeakerService {
    @Bean
    Speaker getSpeakerOfSony() {
        return new Speaker(Constants.speakerBrand.SONY, 1000.0);
    }

    @Bean
    Speaker getSpeakerBose() {
        return new Speaker(Constants.speakerBrand.BOSE, 900.0);
    }


}
