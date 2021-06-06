package com.example.ISA_project.repository;

import com.example.ISA_project.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Reservation findBySerialNumber(String serial_number);
    void delete(Reservation reservation);
    List<Reservation> findAll();

}
