package com.example.ISA_project.controller;

import com.example.ISA_project.model.Complaint;
import com.example.ISA_project.model.dto.ComplaintDTO;
import com.example.ISA_project.model.dto.ReviewDTO;
import com.example.ISA_project.service.IComplaintService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {
    private final IComplaintService complaintService;

    public ComplaintController(IComplaintService complaintService){this.complaintService = complaintService;}

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Complaint> saveComplaint(@RequestBody Complaint complaint){
        return new ResponseEntity<>(complaintService.saveComplaint(complaint), HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Complaint> getAll(){
        return complaintService.findAll();
    }

    @PutMapping("/edit")
    public Complaint editComplaint(@RequestBody Complaint c){
        return complaintService.editComplaint(c);
    }


}
