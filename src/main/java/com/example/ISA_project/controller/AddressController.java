package com.example.ISA_project.controller;

import com.example.ISA_project.model.Address;
import com.example.ISA_project.service.IAddressService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final IAddressService addressService;

    public AddressController(IAddressService addressService){
        this.addressService = addressService;
    }

    @PostMapping(value="/add")
    public Address addAddress(@RequestBody Address address){
        return addressService.addAddress(address);
    }
}
