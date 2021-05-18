package com.example.ISA_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="userEntity")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String state;
    private String town;
    private String phone;
    private Gender gender;
    private LocalDate dateOfBirth;
    private UserType userType;

    public String getFullAdress()
    {
        return address + ", " + town + ", " + state;
    }

}
