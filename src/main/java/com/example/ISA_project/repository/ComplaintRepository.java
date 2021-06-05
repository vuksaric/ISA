package com.example.ISA_project.repository;

import com.example.ISA_project.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {
    Complaint save(Complaint complaint);
    List<Complaint> findAll();
    //Complaint findOneById();
}
