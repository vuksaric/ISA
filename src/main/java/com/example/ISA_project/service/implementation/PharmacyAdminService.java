package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.PharmacyAdmin;
import com.example.ISA_project.repository.PharmacyAdminRepository;
import com.example.ISA_project.service.IPharmacyAdminService;
import org.springframework.stereotype.Service;

@Service
public class PharmacyAdminService implements IPharmacyAdminService {

    private final PharmacyAdminRepository pharmacyAdminRepository;

    public PharmacyAdminService(PharmacyAdminRepository pharmacyAdminRepository){this.pharmacyAdminRepository = pharmacyAdminRepository;}

    @Override
    public PharmacyAdmin getByUserId(int user_id) {
        return pharmacyAdminRepository.findOneByUserId(user_id);
    }
}
