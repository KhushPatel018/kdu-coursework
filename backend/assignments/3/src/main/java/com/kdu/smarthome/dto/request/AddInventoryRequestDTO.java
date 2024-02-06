package com.kdu.smarthome.dto.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddInventoryRequestDTO {
    @JsonProperty("kickston_id")
    private String kickstonId;
    @JsonProperty("device_username")
    private String deviceUsername;
    @JsonProperty("device_password")
    private String devicePassword;
    @JsonProperty("manufacture_factory_place")
    private String manufactureFactoryPlace;
    @JsonProperty("manufacture_date_time")
    private String manufactureDateTime;
}
