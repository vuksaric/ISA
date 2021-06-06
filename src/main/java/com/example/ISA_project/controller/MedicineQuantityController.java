package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.service.IMedicineQuantityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicineQuantity")
public class MedicineQuantityController {
    private final IMedicineQuantityService medicineQuantityService;

    public MedicineQuantityController(IMedicineQuantityService medicineQuantityService) {
        this.medicineQuantityService = medicineQuantityService;
    }

    @GetMapping(value = "/medicineDifference/{id}")
    public ResponseEntity getMedicineDifference(@PathVariable String id) {
        try {
            Integer pharmacyId = Integer.parseInt(id);
            return new ResponseEntity<>(medicineQuantityService.getDifference(pharmacyId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
