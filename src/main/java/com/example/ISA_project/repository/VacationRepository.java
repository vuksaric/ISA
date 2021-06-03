package com.example.ISA_project.repository;

import com.example.ISA_project.model.Vacation;
import com.example.ISA_project.model.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Integer> {
    Vacation save(Vacation vacation);
}
