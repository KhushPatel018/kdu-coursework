package com.kdu.smarthome.entity;
import com.kdu.smarthome.constant.RoleUser;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class HouseOwner extends Metadata{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String houseOwnerId;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private RoleUser role;

    @Override
    public String toString() {
        return "HouseOwner{" +
                "houseOwnerId=" + houseOwnerId +
                ", house=" + house +
                ", user=" + user +
                ", role=" + role +
                '}';
    }

}
