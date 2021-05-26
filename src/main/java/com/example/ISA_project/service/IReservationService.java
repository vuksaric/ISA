package com.example.ISA_project.service;

import com.example.ISA_project.model.Reservation;
import com.example.ISA_project.model.dto.ReservationDTO;

import java.util.List;

public interface IReservationService {

    List<ReservationDTO> getByPharmacy(String pharmacy);
    Reservation issue(String serial_number);
}
