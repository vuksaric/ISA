package com.example.ISA_project.repository;

import com.example.ISA_project.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Integer> {

    Pharmacist save(Pharmacist pharmacist);
    Pharmacist findOneById(int id);
}
