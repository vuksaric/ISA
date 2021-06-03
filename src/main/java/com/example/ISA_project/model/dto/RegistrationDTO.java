package com.example.ISA_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private String name;
    private String surname;
    private String password;
    private String email;
    private String gender;
    private String address;
    private String state;
    private String town;
    private String phone;
    private LocalDateTime birthday;
}
