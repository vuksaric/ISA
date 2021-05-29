package com.example.ISA_project.repository;

import com.example.ISA_project.model.PatientChart;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientChartRepository extends JpaRepository<PatientChart, Integer> {
    PatientChart findOneById(int id);
    PatientChart save(PatientChart patientChart);
}
