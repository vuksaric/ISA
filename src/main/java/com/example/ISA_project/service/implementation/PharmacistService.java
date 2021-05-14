package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Pharmacist;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.repository.PharmacistRepository;
import com.example.ISA_project.service.IPharmacistService;
import org.springframework.stereotype.Service;

@Service
public class PharmacistService implements IPharmacistService {

    private final PharmacistRepository pharmacistRepository;

    public PharmacistService(PharmacistRepository pharmacistRepository)
    {
        this.pharmacistRepository = pharmacistRepository;
    }

    public ProfileDTO getProfile(int id)
    {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        ProfileDTO profile = new ProfileDTO(pharmacist.getUser().getUsername(),pharmacist.getUser().getName(), pharmacist.getUser().getSurname(),
                pharmacist.getUser().getEmail(), pharmacist.getUser().getAddress(), pharmacist.getUser().getState(),
                pharmacist.getUser().getTown(), pharmacist.getUser().getPhone());
        return profile;
    }
}
