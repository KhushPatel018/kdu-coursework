package com.kdu.smarthome.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends Metadata{
    @Id
    @Column(length = 6)
    @JsonProperty("kickston_id")
    private String kickstonId;
    @JsonProperty("device_username")
    private String deviceUsername;
    @JsonProperty("device_password")
    private String devicePassword;
    @JsonProperty("manufacture_date_time")
    private String manufactureDateTime;
    @JsonProperty("manufacture_factory_place")
    private String manufactureFactoryPlace;
}
