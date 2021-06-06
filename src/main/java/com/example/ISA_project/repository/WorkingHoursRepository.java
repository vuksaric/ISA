package com.example.ISA_project.repository;

import com.example.ISA_project.model.WorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingHoursRepository extends JpaRepository<WorkingHours,Integer> {
    WorkingHours save(WorkingHours workingHours);
}
