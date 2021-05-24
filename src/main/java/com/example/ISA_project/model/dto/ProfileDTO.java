package com.example.ISA_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String state;
    private String town;
    private String phone;
}
