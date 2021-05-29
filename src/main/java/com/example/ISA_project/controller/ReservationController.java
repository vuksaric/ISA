package com.example.ISA_project.controller;

import com.example.ISA_project.model.Reservation;
import com.example.ISA_project.model.dto.ReservationDTO;
import com.example.ISA_project.model.dto.ReservationRequest;
import com.example.ISA_project.service.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final IReservationService reservationService;

    public ReservationController(IReservationService reservationService)
    {
        this.reservationService = reservationService;
    }

    @PutMapping(value = "/issue")
    //@PreAuthorize("hasAuthority('LOGIN')")
    public Reservation issue(@RequestBody String serial_number){
        return reservationService.issue(serial_number);
    }

    @PostMapping(consumes = "application/json")
    public void makeReservation(@RequestBody ReservationRequest reservationRequest){
        reservationService.makeReservation(reservationRequest);
    }

    @DeleteMapping(value="/delete/{serialNumber}")
    public ResponseEntity<Boolean> cancelReservation(@PathVariable String serialNumber){
        if(reservationService.cancelReservation(serialNumber)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
