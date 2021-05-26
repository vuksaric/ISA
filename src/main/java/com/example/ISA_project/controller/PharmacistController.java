package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.model.dto.ReservationDTO;
import com.example.ISA_project.model.dto.WorkDayDTO;
import com.example.ISA_project.service.IPharmacistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/getWorkdays/{id}")
    public List<WorkDayDTO> getWorkdays(@PathVariable String id){
        int idPharmacist= Integer.parseInt(id);
        return pharmacistService.getWorkdays(idPharmacist);
    }

    @GetMapping(value = "/getReservations/{id}")
    public List<ReservationDTO> getReservations(@PathVariable String id){
        int idPharmacist= Integer.parseInt(id);
        return pharmacistService.getReservations(idPharmacist);
    }



}
