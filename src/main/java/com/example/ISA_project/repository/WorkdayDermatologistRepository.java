package com.example.ISA_project.repository;

import com.example.ISA_project.model.WorkdayDermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkdayDermatologistRepository extends JpaRepository<WorkdayDermatologist, Integer> {

    WorkdayDermatologist findOneById(int id);

}
