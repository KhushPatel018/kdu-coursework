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
public class RegisterHouseRequestDTO {
    @JsonProperty("house_name")
    private String houseName;
    private String address;
}
