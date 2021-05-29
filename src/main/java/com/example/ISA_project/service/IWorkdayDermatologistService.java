package com.example.ISA_project.service;

import com.example.ISA_project.model.dto.AppointmentDTO;

import java.util.List;

public interface IWorkdayDermatologistService {

    List<AppointmentDTO> getExaminations(int id);

}
