package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Pharmacy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyDTO {
    private String name;
    private float mark;
    private String street;
    private String town;
    private String state;
    private String description;
    private int id;
    private float price;
    private double longitude;
    private double latitude;

    public PharmacyDTO(Pharmacy pharmacy){
        this.name = pharmacy.getName();
        this.street = pharmacy.getAddress().getStreet();
        this.town = pharmacy.getAddress().getTown();
        this.state = pharmacy.getAddress().getState();
        this.description = pharmacy.getDescription();
        this.mark = pharmacy.getMark();
        this.id = pharmacy.getId();
        this.price = pharmacy.getConsultationPrice();
        this.longitude = pharmacy.getAddress().getLongitude();
        this.latitude = pharmacy.getAddress().getLatitude();
    }


}
