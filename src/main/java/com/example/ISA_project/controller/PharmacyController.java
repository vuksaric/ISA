package com.example.ISA_project.controller;

import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.VacationRequest;
import com.example.ISA_project.model.dto.PharmacistDTO;
import com.example.ISA_project.model.dto.PharmacyDTO;
import com.example.ISA_project.service.IPharmacyService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

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

    @GetMapping(value="/allDermatologist/{id}")
    public List<PharmacyDTO> findAllDermatologist(@PathVariable String id){
        int idDermatologist = Integer.parseInt(id);
        return pharmacyService.findAllDermatologist(idDermatologist);
    }

    @GetMapping(value="/getPharmaciesWithFreeAppointment/{date}")
    public Set<PharmacyDTO> getPharmaciesWithFreeAppointment(@PathVariable String date){
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return pharmacyService.getPharmaciesWithFreeAppointment(dateTime);
    }
    @GetMapping(value="/getAvailablePharmacistsByPharmacy/{id}/{date}")
    public List<PharmacistDTO> getAvailablePharmacistsByPharmacy(@PathVariable String id, @PathVariable String date){
        int pharmacyId = Integer.parseInt(id);
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return pharmacyService.getAvailablePharmacistsByPharmacy(pharmacyId, dateTime);
    }

}
