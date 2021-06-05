package com.example.ISA_project.controller;


import com.example.ISA_project.model.Offer;
import com.example.ISA_project.service.IOfferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferController {

    private final IOfferService offerService;

    public OfferController(IOfferService offerService){this.offerService = offerService;}

    @GetMapping("getAllBySupplier/{email}")
    public List<Offer> getAllBySupplier(@PathVariable String email){
        return offerService.getAllBySupplier(email);
    }
}
