package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.OrderList;
import com.example.ISA_project.repository.OrderRepository;
import com.example.ISA_project.service.IOrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void newOrder(OrderList order) {
        try{
            orderRepository.save(order);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int id) {
        try{
            Optional<OrderList> orderList = orderRepository.findById(id);
            if(orderList.isPresent()){
                if(orderList.get().getOffers().size() != 0)
                    orderRepository.delete(orderList.get());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderList> getAll() {
        return orderRepository.findAll();
    }
}
