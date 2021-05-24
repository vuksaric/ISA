package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.UserType;
import com.example.ISA_project.model.VacationRequest;
import com.example.ISA_project.repository.VacationRequestRepository;
import com.example.ISA_project.service.IVacationRequestService;
import org.springframework.stereotype.Service;

@Service
public class VacationRequestService implements IVacationRequestService {

    private final VacationRequestRepository vacationRequestRepository;

    public VacationRequestService(VacationRequestRepository vacationRequestRepository)
    {
        this.vacationRequestRepository = vacationRequestRepository;
    }


    public VacationRequest sendVacationRequestPharmacist(VacationRequest vacationRequest) {
        vacationRequest.setUser_type(UserType.Pharmacist);
        return vacationRequestRepository.save(vacationRequest);
    }
}
