package com.example.ISA_project.controller;

import com.example.ISA_project.model.OrderList;
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
    public ResponseEntity newOrder (@RequestBody OrderList orderList){
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

    @GetMapping(value = "/getAll")
    public ResponseEntity getAll(){
        try{
            return new ResponseEntity<>(orderService.getAll(),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
