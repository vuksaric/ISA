package com.example.ISA_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String password;
    private String name;
    private String surname;
    private String email;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;
    private String phone;
    private LocalDateTime dateOfBirth;
    private Gender gender;
    private UserType userType;
    private boolean passwordChanged;
    @Column(nullable = true)
    private boolean activated;


    public String getFullName() { return name + " " + surname;}


}
