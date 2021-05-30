package com.example.ISA_project.controller;

import com.example.ISA_project.model.VacationRequest;
import com.example.ISA_project.model.dto.VacationDTO;
import com.example.ISA_project.service.IVacationRequestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacationRequest")
public class VacationRequestController {

    private final IVacationRequestService vacationRequestService;

    public VacationRequestController(IVacationRequestService vacationRequestService)
    {
        this.vacationRequestService = vacationRequestService;
    }

    @PostMapping(value = "/vacationRequestPharmacist")
    public VacationRequest sendVacationRequestPharmacist(@RequestBody VacationRequest request){
        return vacationRequestService.sendVacationRequestPharmacist(request);
    }

    @PostMapping(value = "/approval")
    public VacationDTO approveRequest(@RequestBody VacationDTO vacationDTO){
        // to be continued
        return null;
    }

    @PostMapping(value = "/rejection")
    public VacationDTO rejectRequest(@RequestBody VacationDTO vacationDTO){
        // to be continued
        return null;
    }
}
