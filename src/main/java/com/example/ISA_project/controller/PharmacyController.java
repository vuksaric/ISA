package com.example.ISA_project.controller;

import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.VacationRequest;
import com.example.ISA_project.model.dto.*;
import com.example.ISA_project.service.IPharmacyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @PutMapping(value="/removehMedicineQuantity/{id1}/{id2}")
    public ResponseEntity removeMedicineQuantity(@PathVariable String id1, @PathVariable String id2 ){
        int medicineId = Integer.parseInt(id1);
        int pharmacyId = Integer.parseInt(id2);
        try{
            return new ResponseEntity(pharmacyService.removeMedicineQuantity(medicineId,pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/allMedicinesInPharmacy/{id}")
    public ResponseEntity getMedicinesPharmacy(@PathVariable String id){
        int pharmacyId = Integer.parseInt(id);
        try{
            return new ResponseEntity(pharmacyService.getAllMedicinesInPharmacy(pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/searchMedicineQuantity/{input}/{id}")
    public ResponseEntity searchMedicineQuantity(@PathVariable String id,@PathVariable String input){
        int pharmacyId = Integer.parseInt(id);
        try{
            return new ResponseEntity(pharmacyService.search(input,pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/pharmacistInPharmacy/{input}/{id}")
    public ResponseEntity searchPharmacistInPharmacy(@PathVariable String input,@PathVariable String id){
        int pharmacyId = Integer.parseInt(id);
        try{
            return new ResponseEntity(pharmacyService.searchPharmacistInPharmacy(input,pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/addPharmacistInPharmacy/{id}")
    public ResponseEntity addPharmacistInPharmacy(@PathVariable String id, @RequestBody RegistrationDTO registrationDTO){
        int pharmacyId = Integer.parseInt(id);
        try{
            return new ResponseEntity(pharmacyService.addPharmacistInPharmacy(registrationDTO, pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/removePharmacistInPharmacy/{id1}/{id2}")
    public ResponseEntity removePharmacistInPharmacy(@PathVariable String id1, @PathVariable String id2){
        int pharmacyId = Integer.parseInt(id2);
        int pharmacistId = Integer.parseInt(id1);
        try{
            return new ResponseEntity(pharmacyService.removePharmacistInPharmacy(pharmacistId, pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/dermatologistInPharmacy/{input}/{id}")
    public ResponseEntity searchDermatologistInPharmacy(@PathVariable String input,@PathVariable String id){
        int pharmacyId = Integer.parseInt(id);
        try{
            return new ResponseEntity(pharmacyService.searchDermatologistInPharmacy(input,pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/addDermatologistInPharmacy/{id1}/{id2}")
    public ResponseEntity addDermatologistInPharmacy(@PathVariable String id1, @PathVariable String id2, @RequestBody WorkingHoursDTO workingHoursDTO){
        int pharmacyId = Integer.parseInt(id1);
        int dermatologistId = Integer.parseInt(id2);
        try{
            return new ResponseEntity(pharmacyService.addDermatologistInPharmacy(dermatologistId, pharmacyId, workingHoursDTO),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/removeDermatologistInPharmacy/{id1}/{id2}")
    public ResponseEntity removeDermatologistInPharmacy(@PathVariable String id1, @PathVariable String id2){
        int pharmacyId = Integer.parseInt(id2);
        int pharmacistId = Integer.parseInt(id1);
        try{
            return new ResponseEntity(pharmacyService.removeDermatologistInPharmacy(pharmacistId, pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/getDifferenceDermatologist/{id}")
    public ResponseEntity getDifferenceDermatologist(@PathVariable String id){
        int pharmacyId = Integer.parseInt(id);
        try{
            return new ResponseEntity(pharmacyService.getDermatologistDifference(pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/getWorkingHours")
    public ResponseEntity getDermatologistShift(@RequestBody DermatologistFreePeriodsRequestDTO d) throws ParseException {
        try{
            return new ResponseEntity(pharmacyService.getDermatologistShift(d), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/makeReservation")
    public ResponseEntity makeReservation(@RequestBody NewExaminationDTO newExaminationDTO) throws ParseException {
        try{
            pharmacyService.makeReservation(newExaminationDTO);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
