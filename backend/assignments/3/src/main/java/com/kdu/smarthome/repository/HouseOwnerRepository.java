package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.HouseOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseOwnerRepository extends JpaRepository<HouseOwner,String > {
    public HouseOwner findByHouse_IdAndUser_Username(String houseId, String username);
}
