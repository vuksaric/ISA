package com.example.ISA_project.service;

import com.example.ISA_project.model.dto.WorkDayDTO;

import java.util.List;

public interface IDermatologistService {

    List<WorkDayDTO> getWorkdays(int id);
}
