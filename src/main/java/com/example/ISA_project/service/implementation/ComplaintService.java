package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Complaint;
import com.example.ISA_project.model.dto.ComplaintDTO;
import com.example.ISA_project.repository.ComplaintRepository;
import com.example.ISA_project.service.IComplaintService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintService implements IComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository){this.complaintRepository= complaintRepository;}

    @Override
    public Complaint saveComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint editComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    @Override
    public List<Complaint> findAll() {
        List<Complaint> complaintList = new ArrayList<>();
        List<Complaint> list = new ArrayList<>();
        list.addAll(complaintRepository.findAll());
        /*for(Complaint c : list){
            ComplaintDTO cdto = new ComplaintDTO(c.getId(),c.getPatientId(),c.toString(c));
            dtos.add(cdto);
        }*/
        return list;
    }
}
