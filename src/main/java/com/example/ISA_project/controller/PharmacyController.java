package com.example.ISA_project.controller;

import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.VacationRequest;
import com.example.ISA_project.model.dto.PharmacyDTO;
import com.example.ISA_project.service.IPharmacyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {
    private final IPharmacyService pharmacyService;

    public PharmacyController(IPharmacyService pharmacyService){
        this.pharmacyService = pharmacyService;
    }

    @GetMapping(value="/all")
    public List<PharmacyDTO> getPatientInfo(){
        return pharmacyService.findAll();
    }

    @PostMapping(value = "/register")
    public Boolean registerPharmacy(@RequestBody Pharmacy pharmacy){
        return pharmacyService.registerPharmacy(pharmacy);
    }

    @GetMapping(value="/all/{id}")
    public List<PharmacyDTO> findPharmacyByMedicineQuantity(@PathVariable String id){
        int idMedicine = Integer.parseInt(id);
        return pharmacyService.findPharmacyByMedicineQuantity(idMedicine);
    }
    @GetMapping(value="/subscribed/{id}")
    public List<PharmacyDTO> subscribedPharmacies(@PathVariable String id){
        int idPatient = Integer.parseInt(id);
        return pharmacyService.subscribedPharmacies(idPatient);
    }
}
