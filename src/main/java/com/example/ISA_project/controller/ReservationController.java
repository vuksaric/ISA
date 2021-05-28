package com.example.ISA_project.controller;

import com.example.ISA_project.model.Reservation;
import com.example.ISA_project.model.dto.ReservationRequest;
import com.example.ISA_project.service.IReservationService;
import org.springframework.web.bind.annotation.*;

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

}
