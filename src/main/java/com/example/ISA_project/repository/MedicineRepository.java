package com.example.ISA_project.repository;

import com.example.ISA_project.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    @Query("select distinct m.name from Medicine m")
    List<String> getDistinctName();

    List<Medicine> getAllByName(String name);
}
