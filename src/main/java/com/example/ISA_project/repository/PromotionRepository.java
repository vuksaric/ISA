package com.example.ISA_project.repository;

import com.example.ISA_project.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Action, Integer> {
}
