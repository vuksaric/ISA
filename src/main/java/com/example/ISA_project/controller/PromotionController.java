package com.example.ISA_project.controller;

import com.example.ISA_project.model.Action;
import com.example.ISA_project.model.dto.ActionDTO;
import com.example.ISA_project.service.IPromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

    private final IPromotionService promotionService;

    public PromotionController(IPromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping(value= "/new")
    public ResponseEntity addDermatologist(@RequestBody ActionDTO action){
        try{
            promotionService.newPromotion(action);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value= "/all")
    public ResponseEntity getAll(){
        try{
            return new ResponseEntity<>(promotionService.getAll(),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
