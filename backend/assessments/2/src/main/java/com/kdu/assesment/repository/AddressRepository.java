package com.kdu.assesment.repository;

import com.kdu.assesment.entity.Address;
import com.kdu.assesment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
