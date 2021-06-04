package com.example.ISA_project.model.dto;
import com.example.ISA_project.model.Gender;
import com.example.ISA_project.model.Pharmacist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacistDTO {
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
    private float grade;

    public PharmacistDTO(Pharmacist p){
        this.id = p.getId();
        this.name = p.getUser().getName();
        this.surname = p.getUser().getSurname();
        this.email = p.getUser().getEmail();
        if(p.getUser().getGender() == Gender.Male){
            this.gender = "Male";
        }
        else if(p.getUser().getGender() == Gender.Female){
            this.gender = "Female";
        }
        else{
            this.gender ="Non-binary";
        }
        this.address = p.getUser().getAddress().getStreet();
        this.state = p.getUser().getAddress().getState();
        this.town = p.getUser().getAddress().getTown();
        this.phone = p.getUser().getPhone();
        this.birthday = p.getUser().getDateOfBirth().toString();
    }

    public PharmacistDTO(String fullName, float grade, int id){
        this.name=fullName;
        this.grade= grade;
        this.id = id;
    }
}

