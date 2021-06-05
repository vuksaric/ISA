package com.example.ISA_project.service;

import com.example.ISA_project.model.PharmacyAdmin;

public interface IPharmacyAdminService {
    PharmacyAdmin getByUserId(int user_id);
}
