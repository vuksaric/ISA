package com.example.ISA_project.controller;

import com.example.ISA_project.model.OrderList;
import com.example.ISA_project.service.IOfferService;
import com.example.ISA_project.service.IOrderListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderList")
public class OrderListController {
    private final IOrderListService orderListService;
    public OrderListController(IOrderListService orderListService){this.orderListService = orderListService;}

    @GetMapping("/getAll")
    public List<OrderList> getAll(){
        return orderListService.findAll();
    }
}
