package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.OrderList;
import com.example.ISA_project.repository.OrderListRepository;
import com.example.ISA_project.service.IOrderListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderListService implements IOrderListService {

    private final OrderListRepository orderListRepository;
    public OrderListService(OrderListRepository orderListRepository){this.orderListRepository = orderListRepository;}

    @Override
    public List<OrderList> findAll() {
        return orderListRepository.findAll();
    }
}
