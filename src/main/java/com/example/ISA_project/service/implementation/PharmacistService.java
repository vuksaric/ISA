package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Pharmacist;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.repository.PharmacistRepository;
import com.example.ISA_project.service.IPharmacistService;
import org.springframework.stereotype.Service;

@Service
public class PharmacistService implements IPharmacistService {

    private final PharmacistRepository pharmacistRepository;
    private final UserService userService;

    public PharmacistService(PharmacistRepository pharmacistRepository, UserService userService)
    {
        this.pharmacistRepository = pharmacistRepository;
        this.userService = userService;
    }

    public ProfileDTO getProfile(int id)
    {
        return userService.getProfile(id);
    }
}
