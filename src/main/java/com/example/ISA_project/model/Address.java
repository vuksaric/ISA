package com.example.ISA_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street;
    private String town;
    private String state;
    private double latitude;
    private double longitude;

    public String getFullAdress() { return street + ", " + town + ", " + state;}

    public Address(String street, String town, String state){
        this.street = street;
        this.town = town;
        this.state = state;
    }

    public Address(String street, String town, String state, Double latitude, Double longitude){
        this.street = street;
        this.town = town;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
