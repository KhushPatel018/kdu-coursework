package com.kdu.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.sql.Date;
import java.util.UUID;


@Data
@AllArgsConstructor
public class ShiftDTO {
    UUID shiftTypeId;
    String name;
    Date dateStart;
    Date dateEnd;
    Time timeStart;
    Time timeEnd;
    String timezone;
    UUID tenantId;
}
