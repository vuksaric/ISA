package com.example.ISA_project.repository;

import com.example.ISA_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findOneByUsername(String username);
    User findOneById(int id);
    User save(User user);

}
