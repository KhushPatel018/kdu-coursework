package com.kdu.assesment.controller;

import com.kdu.assesment.entity.Address;
import com.kdu.assesment.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @PostMapping
    public ResponseEntity<String> addAddress(@RequestBody Address address){
        addressService.addAddress(address);
        return ResponseEntity.ok("Address created for user " + address.getUser().getId());
    }
    @GetMapping
    public ResponseEntity<List<Address>> getAll(){
        return ResponseEntity.ok(addressService.getAll());
    }
}
