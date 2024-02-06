package com.kdu.smarthome.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddDeviceRequestDTO {
    private String kickstonId;
    private String roomId;
    private String houseId;
}
