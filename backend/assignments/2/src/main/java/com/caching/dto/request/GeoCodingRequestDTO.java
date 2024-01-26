package com.caching.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeoCodingRequestDTO {
    Double latitude;
    Double longitude;
}
