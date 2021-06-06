package com.example.ISA_project.controller;

import com.example.ISA_project.model.MedicineNotification;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.MedicineNotificationDTO;
import com.example.ISA_project.service.IMedicineNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class MedicineNotificationController {

    private final IMedicineNotificationService medicineNotificationService;

    public MedicineNotificationController(IMedicineNotificationService medicineNotificationService) {
        this.medicineNotificationService = medicineNotificationService;
    }

    @GetMapping("/getAll/{id}")
    public ResponseEntity getAll(@PathVariable String id) {
        int pharmacyId = Integer.parseInt(id);
        try {
            List<MedicineNotificationDTO> retVal = medicineNotificationService.getAll(pharmacyId);
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
