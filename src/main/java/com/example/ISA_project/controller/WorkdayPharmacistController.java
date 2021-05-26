package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.AppointmentDTO;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.service.IWorkdayPharmacistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workdayPharmacist")
public class WorkdayPharmacistController {

    private final IWorkdayPharmacistService workdayPharmacistService;

    public WorkdayPharmacistController(IWorkdayPharmacistService workdayPharmacistService)
    {
        this.workdayPharmacistService = workdayPharmacistService;
    }

    @GetMapping(value = "/getConsultations/{id}")
    public List<AppointmentDTO> getConsultations(@PathVariable String id){
        int idWorkday= Integer.parseInt(id);
        return workdayPharmacistService.getConsultations(idWorkday);
    }

}
