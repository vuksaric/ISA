package com.example.ISA_project.repository;

import com.example.ISA_project.model.PricelistMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface PriceListMedicineRepository extends JpaRepository<PricelistMedicine, Integer> {

   @Query("select m from PricelistMedicine m where m.medicine.id=?1 and m.pharmacy.id = ?2")
   PricelistMedicine find(int medicineId, int pharmacyId);
}
