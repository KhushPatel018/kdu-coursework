package com.kdu.assesment.service;

import com.kdu.assesment.entity.Address;
import com.kdu.assesment.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}
