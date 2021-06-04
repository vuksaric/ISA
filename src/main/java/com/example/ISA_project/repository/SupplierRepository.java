package com.example.ISA_project.repository;

import com.example.ISA_project.model.User;
import com.example.ISA_project.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Supplier findOneByUser(User user);
    Supplier findOneById(int id);
    Supplier findOneByUserId(int user_id);
}
