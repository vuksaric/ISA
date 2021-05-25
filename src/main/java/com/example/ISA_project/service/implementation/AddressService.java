package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Address;
import com.example.ISA_project.repository.AddressRepository;
import com.example.ISA_project.service.IAddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }
}
