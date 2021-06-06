package com.example.ISA_project.repository;

import com.example.ISA_project.model.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DermatologistRepository extends JpaRepository<Dermatologist, Integer> {

    Dermatologist findOneById(int id);
    List<Dermatologist> findAll();
}
