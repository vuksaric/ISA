package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.ReservationDTO;
import com.example.ISA_project.model.dto.ReservationRequest;
import com.example.ISA_project.repository.ReservationRepository;
import com.example.ISA_project.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final IEmailService emailService;
    private final IPharmacyService pharmacyService;
    private final IPatientService patientService;
    private final IMedicineService medicineService;

    public ReservationService(ReservationRepository reservationRepository,
                              IEmailService emailService, IPharmacyService pharmacyService,
                              IPatientService patientService, IMedicineService medicineService)
    {
        this.reservationRepository = reservationRepository;
        this.emailService = emailService;
        this.pharmacyService = pharmacyService;
        this.patientService = patientService;
        this.medicineService = medicineService;
    }

    @Override
    public List<ReservationDTO> getByPharmacy(String pharmacy) {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO> result = new ArrayList<>();

        for(Reservation reservation : reservations)
        {
            if(reservation.getPharmacy().getName().equalsIgnoreCase(pharmacy) && reservation.getDueDate().isAfter(LocalDateTime.now().plusDays(1))
                    && !reservation.isIssued() && !reservation.isCanceled())
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

    @Override
    public void makeReservation(ReservationRequest reservationRequest) {
        Medicine medicine = medicineService.findOneById(reservationRequest.getIdMedicine());
        Patient patient = patientService.findOneById(reservationRequest.getIdPatient());

        Pharmacy pharmacy = pharmacyService.subtractMedicineQuantity(reservationRequest.getIdMedicine(),reservationRequest.getIdPharmacy());

        Reservation reservation = new Reservation(reservationRequest.getSerialNumber(), reservationRequest.getDueDate(),
                medicine, pharmacy, patient);
        reservationRepository.save(reservation);
        patientService.addReservation(reservationRequest.getIdPatient(), reservation);
    }

    @Override
    public Boolean cancelReservation(String serialNumber) {
        Reservation reservation = reservationRepository.findBySerialNumber(serialNumber);
        if(reservation !=null){
            reservation.setCanceled(true);
            reservationRepository.save(reservation);
            Pharmacy pharmacy = pharmacyService.addMedicineQuantity(reservation.getMedicine().getId(), reservation.getPharmacy().getId());
            return true;
        }
        else{
            return false;
        }
    }


}
