package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FutureReservationDTO {
    private String serialNumber;
    private String date;
    private String medicine;
    private String pharmacy;
    private boolean canBeCanceled;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public FutureReservationDTO(Reservation reservation){
        this.serialNumber = reservation.getSerialNumber();
        this.date = reservation.getDueDate().format(formatter);
        this.medicine = reservation.getMedicine().getMedicineInformation();
        this.pharmacy = reservation.getPharmacy().getName();

        if(reservation.getDueDate().isAfter(LocalDateTime.now().plusDays(1))){
            this.canBeCanceled= true;
        }
        else{
            this.canBeCanceled=false;
        }
    }
}
