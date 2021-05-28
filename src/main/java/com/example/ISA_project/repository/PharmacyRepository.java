package com.example.ISA_project.repository;

import com.example.ISA_project.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
    @Override
    List<Pharmacy> findAll();
    Pharmacy save(Pharmacy pharmacy);
    Pharmacy findOneById(int id);
}
