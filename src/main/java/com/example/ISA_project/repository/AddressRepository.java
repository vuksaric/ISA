package com.example.ISA_project.repository;

import com.example.ISA_project.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    Address save(Address address);
}
