package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.*;
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
    public ResponseEntity<Set<ReviewObjectDTO>> getPatientMedicine(@PathVariable String id){
        int idPatient = Integer.parseInt(id);
        return new ResponseEntity<>(patientChartService.getPatientMedicine(idPatient), HttpStatus.OK);
    }

    @GetMapping(value="/pharmacy/{id}")
    public ResponseEntity<Set<ReviewObjectDTO>> getPatientPharmacy(@PathVariable String id){
        int idPatient = Integer.parseInt(id);
        return new ResponseEntity<>(patientChartService.getPatientPharmacy(idPatient), HttpStatus.OK);
    }

    @GetMapping(value="/eRecipeMedicines/{id}")
    public ResponseEntity<Set<MedicineDTO>> getPatientERecipeMedicines(@PathVariable String id){
        int idPatient = Integer.parseInt(id);
        return new ResponseEntity<>(patientChartService.getPatientERecipeMedicines(idPatient), HttpStatus.OK);
    }

    @GetMapping(value="/previousExaminations/{id}")
    public ResponseEntity<List<ExaminationDTO>> getPatientsPreviousExaminations(@PathVariable String id){
        int patientId = Integer.parseInt(id);
        return new ResponseEntity<>(patientChartService.getPatientPreviousExaminations(patientId), HttpStatus.OK);
    }

    @GetMapping(value="/previousConsultations/{id}")
        public ResponseEntity<List<ConsultationDTO>> getPatientPreviousConsultations(@PathVariable String id){
        int patientId = Integer.parseInt(id);
        return new ResponseEntity<>(patientChartService.getPatientPreviousConsultations(patientId), HttpStatus.OK);
    }

    @GetMapping(value="/upcomingConsultations/{id}")
    public ResponseEntity<List<ConsultationDTO>> getPatientUpcomingConsultations(@PathVariable String id){
        int patientId = Integer.parseInt(id);
        return new ResponseEntity<>(patientChartService.getPatientUpcomingConsultations(patientId), HttpStatus.OK);
    }

    @GetMapping(value="/eRecipe/{id}")
    public ResponseEntity<List<ERecipeDTO>> getPatientERecipes(@PathVariable String id){
        int patientId = Integer.parseInt(id);
        return new ResponseEntity<>(patientChartService.getPatientERecipes(patientId), HttpStatus.OK);
    }
}
