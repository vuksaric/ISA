package com.example.ISA_project.repository;

import com.example.ISA_project.model.PricelistMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface PriceListMedicineRepository extends JpaRepository<PricelistMedicine, Integer> {

    @Query(nativeQuery = true, value="select p from PriceListMedicine p where p.medicine_id=?1 and p.pharmacy_id=?2")
    PricelistMedicine find(int medicineId, int pharmacyId);
}
