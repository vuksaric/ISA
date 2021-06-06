package com.example.ISA_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String state;
    private String town;
    private String phone;
    private String type;
    private LocalDateTime date;

    public ProfileDTO(String name, String surname, String email, String street, String state, String town, String phone, String gold, LocalDateTime dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = street;
        this.state = state;
        this.town = town;
        this.phone = phone;
        this.type = gold;
        this.date = dateOfBirth;
    }
}
