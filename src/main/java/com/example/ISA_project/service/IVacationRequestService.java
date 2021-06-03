package com.example.ISA_project.service;

import com.example.ISA_project.model.VacationRequest;
import com.example.ISA_project.model.dto.VacationDTO;
import com.example.ISA_project.model.dto.VacationRequestDTO;

import java.util.List;

public interface IVacationRequestService {

    VacationRequest sendVacationRequestPharmacist(VacationRequestDTO vacationRequest);
    VacationDTO approve(VacationDTO vacationDTO);
    VacationDTO reject(VacationDTO vacationDTO);
    List<VacationRequest> getAll();
}
