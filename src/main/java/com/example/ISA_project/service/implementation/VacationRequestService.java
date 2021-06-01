package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.VacationDTO;
import com.example.ISA_project.repository.VacationRepository;
import com.example.ISA_project.repository.VacationRequestRepository;
import com.example.ISA_project.service.IVacationRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationRequestService implements IVacationRequestService {

    private final VacationRequestRepository vacationRequestRepository;
    private final VacationRepository vacationRepository;
    private final PharmacistService pharmacistService;
    private final DermatologistService dermatologistService;
    private final EmailService emailService;

    public VacationRequestService(VacationRequestRepository vacationRequestRepository, VacationRepository vacationRepository, PharmacistService pharmacistService, DermatologistService dermatologistService, EmailService emailService)
    {
        this.vacationRequestRepository = vacationRequestRepository;
        this.vacationRepository = vacationRepository;
        this.pharmacistService = pharmacistService;
        this.dermatologistService = dermatologistService;
        this.emailService = emailService;
    }


    public VacationRequest sendVacationRequestPharmacist(VacationRequest vacationRequest) {
        vacationRequest.setUser_type(UserType.Pharmacist);
        return vacationRequestRepository.save(vacationRequest);
    }

    @Override
    public VacationDTO approve(VacationDTO vacationDTO) {
        Vacation vacation = new Vacation(vacationDTO.getStart_date(),vacationDTO.getEnd_date(),vacationDTO.getUser_type(),vacationDTO.getPharmacy_id(),vacationDTO.getUser_id());
        if(vacationDTO.getUser_type() == UserType.Pharmacist){
            pharmacistService.addVacation(vacation);
            vacationRequestRepository.deleteById(vacationDTO.getRequestId());
        }
        else if(vacationDTO.getUser_type() == UserType.Dermatologist){
            dermatologistService.addVacation(vacation);
            vacationRequestRepository.deleteById(vacationDTO.getRequestId());
        }
        return null;
    }

    @Override
    public VacationDTO reject(VacationDTO vacationDTO) {
        if(vacationDTO.getUser_type() == UserType.Pharmacist){
            Pharmacist pharmacist = pharmacistService.getById(vacationDTO.getUser_id());
            emailService.rejectVacationRequest(pharmacist.getUser(),vacationDTO);
            vacationRequestRepository.deleteById(vacationDTO.getRequestId());
        }
        else if(vacationDTO.getUser_type() == UserType.Dermatologist){
            Dermatologist dermatologist = dermatologistService.getById(vacationDTO.getUser_id());
            emailService.rejectVacationRequest(dermatologist.getUser(),vacationDTO);
            vacationRequestRepository.deleteById(vacationDTO.getRequestId());
        }
       return null;
    }

    @Override
    public List<VacationRequest> getAll() {
        return vacationRequestRepository.findAll();
    }
}
