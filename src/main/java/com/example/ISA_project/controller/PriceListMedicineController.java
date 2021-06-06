package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.PriceListMedicineDTO;
import com.example.ISA_project.service.IPriceListMedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pricelist")
public class PriceListMedicineController {
    private final IPriceListMedicineService priceListMedicineService;

    public PriceListMedicineController(IPriceListMedicineService priceListMedicineService) {
        this.priceListMedicineService = priceListMedicineService;
    }

    @GetMapping(value="/all/{id}")
    public ResponseEntity getMedicinesInPharmacy(@PathVariable String id){
        int pharmacyId = Integer.parseInt(id);
        try{
            return new ResponseEntity(priceListMedicineService.getAll(pharmacyId), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/edit")
    public ResponseEntity editMedicine(@RequestBody PriceListMedicineDTO priceListMedicineDTO){
        try{
            priceListMedicineService.edit(priceListMedicineDTO);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
