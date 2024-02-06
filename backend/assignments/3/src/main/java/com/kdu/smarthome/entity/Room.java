package com.kdu.smarthome.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;



@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Room extends Metadata{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("id")
    private String roomId;
    @JsonProperty("room_name")
    private String roomName;
    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

}
