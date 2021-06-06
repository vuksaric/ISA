package com.example.ISA_project.repository;

import com.example.ISA_project.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    List<Consultation> findAll();
    Consultation findOneById(int id);

    @Query("select e from Consultation e where e.period.start_date > current_date and e.patient.id = ?1 and e.done = false and e.pharmacist.id = ?2")
    List<Consultation> findAllFutureByPatient(int id, int pharmacist);

}
