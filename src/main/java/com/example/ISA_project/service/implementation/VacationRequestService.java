package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.model.dto.VacationAllDTO;
import com.example.ISA_project.model.dto.VacationDTO;
import com.example.ISA_project.repository.VacationRepository;
import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.UserType;
import com.example.ISA_project.model.VacationRequest;
import com.example.ISA_project.model.dto.VacationRequestDTO;
import com.example.ISA_project.repository.VacationRequestRepository;
import com.example.ISA_project.service.IPharmacyService;
import com.example.ISA_project.service.IUserService;
import com.example.ISA_project.service.IVacationRequestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacationRequestService implements IVacationRequestService {

    private final VacationRequestRepository vacationRequestRepository;
    private final VacationRepository vacationRepository;
    private final PharmacistService pharmacistService;
    private final DermatologistService dermatologistService;
    private final EmailService emailService;
    private final IPharmacyService pharmacyService;
    private final IUserService userService;

    public VacationRequestService(VacationRequestRepository vacationRequestRepository, VacationRepository vacationRepository, PharmacistService pharmacistService, DermatologistService dermatologistService, EmailService emailService, IPharmacyService pharmacyService, IUserService userService)
    {
        this.vacationRequestRepository = vacationRequestRepository;
        this.vacationRepository = vacationRepository;
        this.pharmacistService = pharmacistService;
        this.dermatologistService = dermatologistService;
        this.emailService = emailService;
        this.pharmacyService = pharmacyService;
        this.userService = userService;
    }

    @Override
    public VacationRequest sendVacationRequestPharmacist(VacationRequestDTO request) {
        //request.setUser_type(UserType.Pharmacist);
        VacationRequest vacationRequest = null;
        if(request.getUser_type() == UserType.Pharmacist)
        {
            Pharmacist pharmacist = pharmacistService.getById(request.getUser_id());
            vacationRequest = new VacationRequest(request.getStart_date(),request.getEnd_date(),request.getUser_type()
                    ,pharmacist.getPharmacy().getId(),request.getUser_id());
        }
        else
        {
            Pharmacy pharmacy = pharmacyService.findOneById(request.getPharmacy_id());
            vacationRequest = new VacationRequest(request.getStart_date(),request.getEnd_date(),request.getUser_type()
                    ,pharmacy.getId(),request.getUser_id());
        }

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
    public List<VacationAllDTO> getAll(int id) {
        List<VacationAllDTO> vacationRequests = new ArrayList<>();
        ProfileDTO user = null;
        try{
            for(VacationRequest vacationRequest : vacationRequestRepository.findAll()){
                if(vacationRequest.getPharmacy_int() == id) {
                    user = userService.getProfile(vacationRequest.getUser_id());
                    vacationRequests.add(new VacationAllDTO(vacationRequest, user));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return vacationRequests;
    }
}
