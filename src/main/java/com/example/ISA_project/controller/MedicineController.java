package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.MedicineAllergyDTO;
import com.example.ISA_project.service.IMedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ISA_project.model.dto.MedicineDTO;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    private final IMedicineService medicineService;
    public MedicineController(IMedicineService medicineService){
        this.medicineService = medicineService;
    }


    @GetMapping("/distinct")
    public ResponseEntity<List<MedicineAllergyDTO>> getDistinctMedicine() {
        return new ResponseEntity<>(medicineService.getDistinctMedicine(), HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/{id}")
    public ResponseEntity getMedicine(@PathVariable String id){
        try{
            Integer medicineId = Integer.parseInt(id);
            MedicineDTO retVal = medicineService.getMedicineById(medicineId);
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        try{
            List<MedicineDTO> retVal = medicineService.getAllMedicine();
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        try{
            Integer medicineId = Integer.parseInt(id);
            medicineService.deleteMedicine(medicineId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllByName/{name}")
    public ResponseEntity getAllByName(@PathVariable String name){
        try{
            List<MedicineDTO> retVal = medicineService.getAllByName(name);
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
