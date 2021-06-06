package com.example.ISA_project.controller;

import com.example.ISA_project.model.OrderList;
import com.example.ISA_project.model.dto.AcceptOfferDTO;
import com.example.ISA_project.model.dto.MedicineOrderDTO;
import com.example.ISA_project.model.dto.RegistrationDTO;
import com.example.ISA_project.service.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/new")
    public ResponseEntity newOrder (@RequestBody MedicineOrderDTO orderList){
        try{
            orderService.newOrder(orderList);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody int id){
        try{
            orderService.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAll/{id}")
    public ResponseEntity getAll(@PathVariable String id){
        int pharmacyId= Integer.parseInt(id);
        try{
            return new ResponseEntity<>(orderService.getAll(pharmacyId),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/acceptOffer")
    public ResponseEntity newOrder (@RequestBody AcceptOfferDTO acceptOfferDTO){
        try{
            orderService.acceptOffer(acceptOfferDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
