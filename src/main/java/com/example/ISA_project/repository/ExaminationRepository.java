package com.example.ISA_project.repository;

import com.example.ISA_project.model.Dermatologist;
import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Integer> {

    @Query("select e from Examination e where e.free = true")
    List<Examination> findAllFree();

    Examination save(Examination examination);

    Examination findExaminationById(int id);

    @Query("select e from Examination e where e.date.start_date > current_date and e.patient.id = ?1")
    List<Examination> findAllFutureByPatient(int id);
}
