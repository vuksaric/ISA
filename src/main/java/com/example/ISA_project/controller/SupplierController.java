package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.service.ISupplierService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    private final ISupplierService supplierService;

    public SupplierController(ISupplierService supplierService){this.supplierService = supplierService;}

    @GetMapping("/getByEmail/{email}")
    public ProfileDTO getByEmail(@PathVariable String email){
        return supplierService.getByEmail(email);
    }
}
