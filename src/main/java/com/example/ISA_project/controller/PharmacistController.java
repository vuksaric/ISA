package com.example.ISA_project.controller;

import com.example.ISA_project.model.Pharmacist;
import com.example.ISA_project.model.dto.*;
import com.example.ISA_project.service.IPharmacistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacist")
public class PharmacistController {

    private final IPharmacistService pharmacistService;

    public PharmacistController(IPharmacistService pharmacistService)
    {
        this.pharmacistService = pharmacistService;
    }

    @GetMapping(value = "/getProfile/{id}")
    public ProfileDTO getProfile(@PathVariable String id){
        int idPharmacist= Integer.parseInt(id);
        return pharmacistService.getProfile(idPharmacist);
    }

    @GetMapping(value = "/getWorkdays/{id}")
    public List<WorkDayDTO> getWorkdays(@PathVariable String id){
        int idPharmacist= Integer.parseInt(id);
        return pharmacistService.getWorkdays(idPharmacist);
    }

    @GetMapping(value = "/getReservations/{id}")
    public List<ReservationDTO> getReservations(@PathVariable String id){
        int idPharmacist= Integer.parseInt(id);
        return pharmacistService.getReservations(idPharmacist);
    }

    @PostMapping(value = "/newPharmacist")
    public ResponseEntity newPharmacist(@RequestBody RegistrationDTO registrationDTO){
        try{
            pharmacistService.newPharmacist(registrationDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/all")
    public ResponseEntity getAll(){
        try{
            List<PharmacistDTO> retVal = pharmacistService.getAll();
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/search/{input}")
    public ResponseEntity search(@PathVariable String input){
        try{
            List<PharmacistDTO> retVal = pharmacistService.search(input);
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
