package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.MedicineAllergyDTO;
import com.example.ISA_project.service.IMedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    private final IMedicineService medicineService;
    public MedicineController(IMedicineService medicineService){
        this.medicineService = medicineService;
    }

    @GetMapping("/distinct")
    public ResponseEntity<List<MedicineAllergyDTO>> getDistinctMedicine(){
        return new ResponseEntity<>(medicineService.getDistinctMedicine(), HttpStatus.OK );
    }
}
