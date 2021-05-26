package com.example.ISA_project.repository;

import com.example.ISA_project.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresriptionRepository extends JpaRepository<Prescription, Integer> {

    Prescription save(Prescription prescription);

}
