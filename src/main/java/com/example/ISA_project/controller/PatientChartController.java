package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.MedicineAllergyDTO;
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
        int patientChartId = Integer.parseInt(id);
        return new ResponseEntity<>(patientChartService.getPatientsAllergies(patientChartId), HttpStatus.OK);
    }

   @PostMapping(consumes = "application/json", value="/addAllergy/{id}")
    public ResponseEntity<Set<MedicineAllergyDTO>> addPatientAllergy(@RequestBody MedicineAllergyDTO allergyDTO,@PathVariable String id){
        int patientChartId = Integer.parseInt(id);
        return  new ResponseEntity<>(patientChartService.addPatientAllergy(allergyDTO, patientChartId),HttpStatus.OK );
    }
}
