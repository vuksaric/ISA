package com.example.ISA_project.service;

import com.example.ISA_project.model.VacationRequest;
import com.example.ISA_project.model.dto.VacationDTO;

import java.util.List;

public interface IVacationRequestService {

    VacationRequest sendVacationRequestPharmacist(VacationRequest vacationRequest);
    VacationDTO approve(VacationDTO vacationDTO);
    VacationDTO reject(VacationDTO vacationDTO);
    List<VacationRequest> getAll();
}
