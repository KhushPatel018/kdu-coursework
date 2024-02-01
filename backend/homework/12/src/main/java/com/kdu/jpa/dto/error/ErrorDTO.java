package com.kdu.jpa.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ErrorDTO {
    String message;
    int statusCode;

}
