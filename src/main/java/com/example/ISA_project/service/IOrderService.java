package com.example.ISA_project.service;

import com.example.ISA_project.model.OrderList;

import java.util.List;

public interface IOrderService {
    void newOrder(OrderList order);
    void deleteOrder(int id);
    List<OrderList> getAll();
}
