package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.AppointmentDTO;
import com.example.ISA_project.service.IWorkdayDermatologistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/workdayDermatologist")
public class WorkdayDermatologistController {

    private final IWorkdayDermatologistService workdayDermatologistService;

    public WorkdayDermatologistController(IWorkdayDermatologistService workdayDermatologistService)
    {
        this.workdayDermatologistService = workdayDermatologistService;
    }

    @GetMapping(value = "/getExaminations/{id}")
    public List<AppointmentDTO> getExaminations(@PathVariable String id){
        int idWorkday= Integer.parseInt(id);
        return workdayDermatologistService.getExaminations(idWorkday);
    }

}
