package com.example.ISA_project.repository;

import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    List<Medicine> findAll();
    Medicine findOneById(int id);
    void delete(Medicine m);

    @Query(nativeQuery = true, value="select * from Medicine m where m.name = LIKE concat('%', ?1, '%')")
    List<Medicine> search(String name);

}
