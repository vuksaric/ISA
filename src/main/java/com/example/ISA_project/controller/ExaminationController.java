package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.ExaminationDTO;
import com.example.ISA_project.repository.PatientRepository;
import com.example.ISA_project.service.IExaminationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/examination")
public class ExaminationController {
    private final IExaminationService examinationService;
    public ExaminationController(IExaminationService examinationService ){
        this.examinationService = examinationService;
    }
    @GetMapping(value="/getFree")
    public ResponseEntity<List<ExaminationDTO>> getAllFree(){
        return new ResponseEntity<>(examinationService.findAllFree(), HttpStatus.OK);
    }

    @PutMapping(value = "/reserve/{id}/{patient}")
    public ResponseEntity<ExaminationDTO> reserveExamination(@PathVariable String id, @PathVariable String patient){
        int idExamination= Integer.parseInt(id);
        int idPatient= Integer.parseInt(patient);
        return new ResponseEntity<>(examinationService.reserveExamination(idExamination,idPatient), HttpStatus.OK);
    }

    @GetMapping(value="/getFuture/{id}")
    public ResponseEntity<List<ExaminationDTO>> getAllFutureByPatient(@PathVariable String id){
        int idPatient= Integer.parseInt(id);
        return new ResponseEntity<>(examinationService.findAllFutureByPatient(idPatient), HttpStatus.OK);
    }

    @PutMapping(value = "/cancel/{id}")
    public ResponseEntity<ExaminationDTO> cancelExamination(@PathVariable String id){
        int idExamination= Integer.parseInt(id);
        return new ResponseEntity<>(examinationService.cancelExamination(idExamination), HttpStatus.OK);
    }
}
