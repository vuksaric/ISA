package com.example.ISA_project.service;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.WorkdayPharmacist;
import com.example.ISA_project.model.dto.AppointmentDTO;
import com.example.ISA_project.model.dto.WorkDayDTO;

import java.util.List;

public interface IWorkdayPharmacistService {

     List<AppointmentDTO> getConsultations(int id);
}
