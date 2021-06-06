package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Offer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {
    private int id;
    private float price;
    private String dueDate;
    private int supplierId;

    public OfferDTO(Offer offer){
        this.id = offer.getId();
        this.price = offer.getPrice();
        this.dueDate = offer.getDueDate().toString();
        this.supplierId = offer.getSupplierId();
    }
}
