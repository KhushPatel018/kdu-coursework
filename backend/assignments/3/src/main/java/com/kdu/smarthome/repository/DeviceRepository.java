package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DeviceRepository extends JpaRepository<Device,String> {
    public List<Device> findByHouse_Id(String houseId);
}
