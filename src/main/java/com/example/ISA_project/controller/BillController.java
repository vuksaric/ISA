package com.example.ISA_project.controller;

import com.example.ISA_project.service.IBillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {

    private final IBillService billService;

    public BillController(IBillService billService) {
        this.billService = billService;
    }

    @GetMapping("/billReport/{id}")
    public ResponseEntity getReport(@PathVariable String id){
        int pharmacyId = Integer.parseInt(id);
        try{
            return new ResponseEntity(billService.billReport(pharmacyId), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/incomeReport/{id}")
    public ResponseEntity getIncome(@PathVariable String id){
        int pharmacyId = Integer.parseInt(id);
        try{
            return new ResponseEntity(billService.incomeReport(pharmacyId), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
