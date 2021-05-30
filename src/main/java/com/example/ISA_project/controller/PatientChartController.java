package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.FutureReservationDTO;
import com.example.ISA_project.model.dto.MedicineAllergyDTO;
import com.example.ISA_project.model.dto.ReviewObjectDTO;
import com.example.ISA_project.service.IPatientChartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/patientChart")
public class PatientChartController {
    private final IPatientChartService patientChartService;

    public PatientChartController(IPatientChartService patientChartService){
        this.patientChartService = patientChartService;
    }
    @GetMapping(value="/allergies/{id}")
    public ResponseEntity<Set<MedicineAllergyDTO>> getPatientAllergies(@PathVariable String id){
        int patientId = Integer.parseInt(id);
        return new ResponseEntity<>(patientChartService.getPatientsAllergies(patientId), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", value="/addAllergy/{id}")
    public ResponseEntity<Set<MedicineAllergyDTO>> addPatientAllergy(@RequestBody MedicineAllergyDTO allergyDTO,@PathVariable String id){
        int patientId = Integer.parseInt(id);
        return  new ResponseEntity<>(patientChartService.addPatientAllergy(allergyDTO, patientId),HttpStatus.OK );
    }

    @GetMapping(value="/futureReservations/{id}")
    public ResponseEntity<List<FutureReservationDTO>> getPatientsFutureReservations(@PathVariable String id){
        int patientId = Integer.parseInt(id);
        return new ResponseEntity<>(patientChartService.getPatientsFutureReservations(patientId), HttpStatus.OK);
    }

    @PutMapping(value="/{id}/remove/{serialNumber}")
    public  ResponseEntity<List<FutureReservationDTO>> removeReservation(@PathVariable String id, @PathVariable String serialNumber){
        int idPatient = Integer.parseInt(id);
        return new ResponseEntity<>(patientChartService.removeReservation(idPatient, serialNumber), HttpStatus.OK);
    }

    @GetMapping(value="/doctors/{id}")
    public ResponseEntity<List<ReviewObjectDTO>> getPatientDoctors(@PathVariable String id){
        int idPatient = Integer.parseInt(id);
        return new ResponseEntity<>(patientChartService.getPatientDermatologist(idPatient), HttpStatus.OK);
    }
    @GetMapping(value="/pharmacist/{id}")
    public ResponseEntity<List<ReviewObjectDTO>> getPatientPharmacit(@PathVariable String id){
        int idPatient = Integer.parseInt(id);
        return new ResponseEntity<>(patientChartService.getPatientPharmacist(idPatient), HttpStatus.OK);
    }

    @GetMapping(value="/medicine/{id}")
    public ResponseEntity<List<ReviewObjectDTO>> getPatientMedicine(@PathVariable String id){
        int idPatient = Integer.parseInt(id);
        return new ResponseEntity<>(patientChartService.getPatientMedicine(idPatient), HttpStatus.OK);
    }
}
