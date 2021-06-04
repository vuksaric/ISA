package com.example.ISA_project.controller;

import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.VacationRequest;
import com.example.ISA_project.model.dto.PharmacyDTO;
import com.example.ISA_project.service.IPharmacyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value="/allDermatologist/{id}")
    public List<PharmacyDTO> findAllDermatologist(@PathVariable String id){
        int idDermatologist = Integer.parseInt(id);
        return pharmacyService.findAllDermatologist(idDermatologist);
    }

    @GetMapping(value="/allDermatologistPharmacy/{id}")
    public ResponseEntity getDermatologistInPharmacy(@PathVariable String id){
        int pharmacyId = Integer.parseInt(id);
        try{
            return new ResponseEntity<>(pharmacyService.getAllDermatologists(pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/allPharmacistPharmacy/{id}")
    public ResponseEntity getPharmacistsInPharmacy(@PathVariable String id){
        int pharmacyId = Integer.parseInt(id);
        try{
            return new ResponseEntity(pharmacyService.getAllPharmacists(pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/allMedicinesPharmacy/{id}")
    public ResponseEntity getMedicinesInPharmacy(@PathVariable String id){
        int pharmacyId = Integer.parseInt(id);
        try{
            return new ResponseEntity(pharmacyService.getAllMedicines(pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/getPharmacyMark/{id}")
    public ResponseEntity getMark(@PathVariable String id){
        int pharmacyId = Integer.parseInt(id);
        try{
            return new ResponseEntity(pharmacyService.getMark(pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/getPharmacy/{id}")
    public ResponseEntity getPharmacyById(@PathVariable String id){
        int pharmacyId = Integer.parseInt(id);
        try{
            return new ResponseEntity(pharmacyService.findOneByIdDTO(pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/newMedicineQuantity/{id1}/{id2}")
    public ResponseEntity newMedicineQuantity(@PathVariable String id1, @PathVariable String id2 ){
        int medicineId = Integer.parseInt(id1);
        int pharmacyId = Integer.parseInt(id2);
        try{
            pharmacyService.newMedicineQuantity(medicineId,pharmacyId);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
