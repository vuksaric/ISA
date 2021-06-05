package com.example.ISA_project.repository;

import com.example.ISA_project.model.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderListRepository extends JpaRepository<OrderList,Integer> {
    List<OrderList> findAll();
}
