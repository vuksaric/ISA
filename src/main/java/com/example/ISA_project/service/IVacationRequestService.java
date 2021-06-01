package com.example.ISA_project.service;

import com.example.ISA_project.model.VacationRequest;
import com.example.ISA_project.model.dto.VacationRequestDTO;

public interface IVacationRequestService {

    VacationRequest sendVacationRequestPharmacist(VacationRequestDTO vacationRequest);
}
