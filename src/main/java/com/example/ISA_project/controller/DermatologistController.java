package com.example.ISA_project.controller;

import com.example.ISA_project.service.IDermatologistService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dermatologist")
public class DermatologistController {

    private final IDermatologistService dermatologistService;

    public DermatologistController(IDermatologistService dermatologistService)
    {
        this.dermatologistService = dermatologistService;
    }
}
