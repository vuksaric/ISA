package com.example.ISA_project.repository;

import com.example.ISA_project.model.WorkdayPharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface WorkdayPharmacistRepository extends JpaRepository<WorkdayPharmacist, Integer> {

    WorkdayPharmacist findOneById(int id);

}
