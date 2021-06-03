package com.example.ISA_project.repository;


import com.example.ISA_project.model.VacationRequest;
import com.example.ISA_project.model.dto.VacationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRequestRepository extends JpaRepository<VacationRequest, Integer> {

    VacationRequest save(VacationRequest vacationRequest);

}
