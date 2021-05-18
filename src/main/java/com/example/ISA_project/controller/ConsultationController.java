package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.PreviousConsultationDTO;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.service.IConsultationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    private final IConsultationService consultationService;

    public ConsultationController(IConsultationService consultationService)
    {
        this.consultationService = consultationService;
    }

    @GetMapping(value = "/getPreviousByPharmacist/{id}")
    public List<PreviousConsultationDTO> getPreviousByPharmacist(@PathVariable String id){
        int idPharmacist= Integer.parseInt(id);
        return consultationService.getPreviousByPharmacist(idPharmacist);
    }
}
