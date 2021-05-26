package com.example.ISA_project.repository;

import com.example.ISA_project.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Reservation findBySerialNumber(String serial_number);

}
