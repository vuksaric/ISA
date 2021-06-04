package com.example.ISA_project.service;

import com.example.ISA_project.model.dto.ProfileDTO;

public interface ISupplierService {
    ProfileDTO getSupplierByEmail(String email);
}
