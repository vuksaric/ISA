package com.example.ISA_project.service;

import com.example.ISA_project.model.WorkdayPharmacist;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.model.dto.WorkDayPharmacistDTO;

import java.util.List;


public interface IPharmacistService {

        ProfileDTO getProfile(int id);
        List<WorkDayPharmacistDTO> getWorkdays(int id);
}
