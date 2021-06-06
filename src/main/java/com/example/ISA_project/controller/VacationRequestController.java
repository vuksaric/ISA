package com.example.ISA_project.controller;

import com.example.ISA_project.model.VacationRequest;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.model.dto.VacationAllDTO;
import com.example.ISA_project.model.dto.VacationDTO;
import com.example.ISA_project.model.dto.VacationRequestDTO;
import com.example.ISA_project.service.IUserService;
import com.example.ISA_project.service.IVacationRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vacationRequest")
public class VacationRequestController {

    private final IVacationRequestService vacationRequestService;


    public VacationRequestController(IVacationRequestService vacationRequestService)
    {
        this.vacationRequestService = vacationRequestService;
    }

    @PostMapping(value = "/vacationRequestPharmacist")
    public VacationRequest sendVacationRequestPharmacist(@RequestBody VacationRequestDTO request){
        return vacationRequestService.sendVacationRequestPharmacist(request);
    }

    @PostMapping(value = "/approval")
    public VacationDTO approveRequest(@RequestBody VacationDTO vacationDTO){
        try{
            vacationRequestService.approve(vacationDTO);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/rejection")
    public VacationDTO rejectRequest(@RequestBody VacationDTO vacationDTO){
        try{
            vacationRequestService.reject(vacationDTO);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/allRequests/{id}")
    public List<VacationAllDTO> allRequests(@PathVariable String id){
        List<VacationAllDTO> vacationRequests = new ArrayList<>();
        try{
            int pharmacyId = Integer.parseInt(id);
            vacationRequests = vacationRequestService.getAll(pharmacyId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return vacationRequests;
    }
}
