package com.example.ISA_project.repository;

import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Integer> {

    Pharmacist save(Pharmacist pharmacist);
    Pharmacist findOneById(int id);

    /*@Query(nativeQuery = true, value="select * from Pharmacist p where p.name = LIKE concat('%', ?1, '%')")
    List<Pharmacist> search(String input, int id);*/

    @Query("select p from Pharmacist p where p.user.name LIKE ?1%")
    List<Pharmacist> search(String input);

    @Query("select p from Pharmacist p where p.user.email = ?1")
    List<Pharmacist> uniqueEmail(String email);
}
