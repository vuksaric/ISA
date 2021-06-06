package com.example.ISA_project.repository;


import com.example.ISA_project.model.MedicineNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineNotificationRepository extends JpaRepository<MedicineNotification, Integer> {
}
