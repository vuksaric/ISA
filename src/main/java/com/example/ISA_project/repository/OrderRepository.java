package com.example.ISA_project.repository;

import com.example.ISA_project.model.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderList,Integer> {
    OrderList findById(int id);
}
