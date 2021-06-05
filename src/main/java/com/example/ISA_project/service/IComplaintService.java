package com.example.ISA_project.service;

import com.example.ISA_project.model.Complaint;
import com.example.ISA_project.model.dto.ComplaintDTO;

import java.util.List;

public interface IComplaintService {
    Complaint saveComplaint(Complaint complaint);
    Complaint editComplaint(Complaint complaint);
    List<Complaint> findAll();
}
