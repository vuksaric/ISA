package com.example.ISA_project.service;


import com.example.ISA_project.model.dto.PharmacyDTO;

import java.util.List;

public interface IPharmacyService {
    List<PharmacyDTO> findAll();
}
