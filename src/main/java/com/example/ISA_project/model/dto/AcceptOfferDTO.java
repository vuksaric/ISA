package com.example.ISA_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcceptOfferDTO {
    int pharmacyId;
    int orderListId;
    int offerId;
    int adminId;
}
