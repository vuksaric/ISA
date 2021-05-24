package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.WorkDayDTO;
import com.example.ISA_project.service.IDermatologistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
