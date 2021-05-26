package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.ExaminationDTO;
import com.example.ISA_project.service.IExaminationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/examination")
public class ExaminationController {
    private final IExaminationService examinationService;
    public ExaminationController(IExaminationService examinationService){
        this.examinationService = examinationService;
    }
    @GetMapping(value="/getFree")
    public ResponseEntity<List<ExaminationDTO>> getAllFree(){
        return new ResponseEntity<>(examinationService.findAllFree(), HttpStatus.OK);
    }

    @PutMapping(value = "/reserve/{id}")
    public ResponseEntity<ExaminationDTO> reserveExamination(@PathVariable String id){
        int idExamination= Integer.parseInt(id);
        return new ResponseEntity<>(examinationService.reserveExamination(idExamination), HttpStatus.OK);
    }
}
