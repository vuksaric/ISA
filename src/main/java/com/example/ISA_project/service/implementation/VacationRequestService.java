package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.UserType;
import com.example.ISA_project.model.VacationRequest;
import com.example.ISA_project.model.dto.VacationRequestDTO;
import com.example.ISA_project.repository.VacationRequestRepository;
import com.example.ISA_project.service.IPharmacistService;
import com.example.ISA_project.service.IPharmacyService;
import com.example.ISA_project.service.IVacationRequestService;
import org.springframework.stereotype.Service;

@Service
public class VacationRequestService implements IVacationRequestService {

    private final VacationRequestRepository vacationRequestRepository;
    private final IPharmacyService pharmacyService;

    public VacationRequestService(VacationRequestRepository vacationRequestRepository, IPharmacyService pharmacyService)
    {
        this.vacationRequestRepository = vacationRequestRepository;
        this.pharmacyService = pharmacyService;
    }


    public VacationRequest sendVacationRequestPharmacist(VacationRequestDTO request) {
        //request.setUser_type(UserType.Pharmacist);
        Pharmacy pharmacy = pharmacyService.findOneById(request.getPharmacy_id());
        VacationRequest vacationRequest = new VacationRequest(request.getStart_date(),request.getEnd_date(),request.getUser_type()
                ,pharmacy.getId(),request.getUser_id());
        return vacationRequestRepository.save(vacationRequest);
    }

}
