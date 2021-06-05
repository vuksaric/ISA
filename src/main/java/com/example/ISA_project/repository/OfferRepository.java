package com.example.ISA_project.repository;

import com.example.ISA_project.model.Offer;
import com.example.ISA_project.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Integer> {

    List<Offer> findAll();
    List<Offer> findAllBySupplier(Supplier supplier);

}
