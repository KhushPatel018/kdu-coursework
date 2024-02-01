package com.kdu.jpa.entity;


import lombok.Data;
import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Data
@Entity
@Table(name = "shifts")
public class Shift extends CreateUpdateInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private ShiftType shiftType;

    private String name;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "time_start")
    private Time timeStart;

    @Column(name = "time_end")
    private Time timeEnd;

    @Column(name = "time_zone")
    private String timeZone;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tenant tenant;
}