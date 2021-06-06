package com.example.ISA_project.repository;

import com.example.ISA_project.model.MedicineQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineQuantityRepository extends JpaRepository<MedicineQuantity,Integer> {
    List<MedicineQuantity> findAll();
    MedicineQuantity findById(int id);
}
