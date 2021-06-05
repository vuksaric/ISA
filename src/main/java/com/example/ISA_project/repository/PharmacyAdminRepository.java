package com.example.ISA_project.repository;

import com.example.ISA_project.model.PharmacyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyAdminRepository extends JpaRepository<PharmacyAdmin, Integer> {
    PharmacyAdmin findOneByUserId(int user_id);
}
