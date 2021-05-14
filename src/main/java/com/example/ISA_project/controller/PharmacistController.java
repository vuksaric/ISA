package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.service.IPharmacistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pharmacist")
public class PharmacistController {

    private final IPharmacistService pharmacistService;

    public PharmacistController(IPharmacistService pharmacistService)
    {
        this.pharmacistService = pharmacistService;
    }

    @GetMapping(value = "/getProfile/{id}")
    public ProfileDTO getProfile(@PathVariable String id){
        int idPharmacist= Integer.parseInt(id);
        return pharmacistService.getProfile(idPharmacist);
    }
}
