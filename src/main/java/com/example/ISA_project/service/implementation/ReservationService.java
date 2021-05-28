package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Reservation;
import com.example.ISA_project.model.dto.ReservationDTO;
import com.example.ISA_project.repository.ReservationRepository;
import com.example.ISA_project.service.IEmailService;
import com.example.ISA_project.service.IReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final IEmailService emailService;
    private final PharmacyService pharmacyService;

    public ReservationService(ReservationRepository reservationRepository, IEmailService emailService, PharmacyService pharmacyService)
    {
        this.reservationRepository = reservationRepository;
        this.emailService = emailService;
        this.pharmacyService = pharmacyService;
    }

    @Override
    public List<ReservationDTO> getByPharmacy(String pharmacy) {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO> result = new ArrayList<>();

        for(Reservation reservation : reservations)
        {
            if(reservation.getPharmacy().getName().equalsIgnoreCase(pharmacy) && reservation.getDueDate().isAfter(LocalDateTime.now().plusDays(1)) && !reservation.isIssued())
                result.add(new ReservationDTO(reservation.getSerialNumber(),reservation.getDueDate(),
                        reservation.getMedicine().getName(),reservation.getPatient().getUser().getFullName()));
        }
        return result;
    }

    @Override
    public Reservation issue(String serial_number) {
        Reservation reservation = reservationRepository.findBySerialNumber(serial_number);
        reservation.setIssued(true);
        emailService.issueReservationEmail(reservation.getPatient());
        pharmacyService.prescribeMedicine(reservation.getPharmacy().getId(),reservation.getMedicine().getId());
        return reservationRepository.save(reservation);
    }
}
