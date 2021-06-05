package com.example.ISA_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Offer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float price;
    private LocalDateTime dueDate;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Supplier supplier;
    private OfferStatus status;

    public String getSupplierMail(){
        return supplier.getUser().getEmail();
    }
}
