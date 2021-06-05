package com.example.ISA_project.service;

import com.example.ISA_project.model.Supplier;
import com.example.ISA_project.model.dto.ProfileDTO;

public interface ISupplierService {
    ProfileDTO getByEmail(String email);
    Supplier getByEmailSupplier(String email);
    Supplier getByUserId(int user_id);
}
