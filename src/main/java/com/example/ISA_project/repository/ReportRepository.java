package com.example.ISA_project.repository;


import com.example.ISA_project.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    Report save(Report report);
}
