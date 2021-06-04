package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Dermatologist;
import com.example.ISA_project.model.Gender;
import com.example.ISA_project.model.Pharmacist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DermatologistDTO {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String gender;
    private String address;
    private String state;
    private String town;
    private String phone;
    private String birthday;
    private float mark;
    private String role;

    public DermatologistDTO(Dermatologist d){
        this.id = d.getId();
        this.name = d.getUser().getName();
        this.surname = d.getUser().getSurname();
        this.email = d.getUser().getEmail();
        if(d.getUser().getGender() == Gender.Male){
            this.gender = "Male";
        }
        else if(d.getUser().getGender() == Gender.Female){
            this.gender = "Female";
        }
        else{
            this.gender ="Non-binary";
        }
        this.address = d.getUser().getAddress().getStreet();
        this.state = d.getUser().getAddress().getState();
        this.town = d.getUser().getAddress().getTown();
        this.phone = d.getUser().getPhone();
        this.birthday = d.getUser().getDateOfBirth().toString();
        this.mark = d.getMark();
        this.role = "Dermatologist";
    }
}
