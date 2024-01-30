package com.kdu.jdbc.model;

import com.kdu.jdbc.constant.SHIFT_TYPE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
@Getter
public class Shift {
    private UUID id;
    private UUID shiftTypeId;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Time timeStart;
    private Time timeEnd;
    private Instant createdAt;
    private Instant updatedAt;
    private String timezone;
    private UUID tenantId;
}
