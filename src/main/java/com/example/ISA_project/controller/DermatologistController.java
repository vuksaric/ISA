package com.example.ISA_project.controller;

import com.example.ISA_project.model.Dermatologist;
import com.example.ISA_project.model.dto.CheckVacationRequest;
import com.example.ISA_project.model.dto.DermatologistDTO;
import com.example.ISA_project.model.dto.PharmacistDTO;
import com.example.ISA_project.model.dto.WorkDayDTO;
import com.example.ISA_project.service.IDermatologistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dermatologist")
public class DermatologistController {

    private final IDermatologistService dermatologistService;

    public DermatologistController(IDermatologistService dermatologistService)
    {
        this.dermatologistService = dermatologistService;
    }

    @GetMapping(value = "/getWorkdays/{id}")
    public List<WorkDayDTO> getWorkdays(@PathVariable String id){
        int idDermatologist= Integer.parseInt(id);
        return dermatologistService.getWorkdays(idDermatologist);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAll(){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/search")
    public ResponseEntity search(){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody int id){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value= "/add")
    public ResponseEntity addDermatologist(@RequestBody int id){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/checkVacation")
    public ResponseEntity checkVacation(@RequestBody CheckVacationRequest request){
        try{
            dermatologistService.checkVacation(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
