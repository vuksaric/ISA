package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineOrderDTO {
    private int id;
    private int pharmacyId;
    private int medicineId;
    private int adminId;
    private OfferStatus status;
    private int medicineQuantityId;
    private String medicineName;
    private int medicineQuantity;
    private String dueDate;
    List<OfferDTO> offers = new ArrayList<>();

    public MedicineOrderDTO(OrderList orderList){
        this.id = orderList.getId();
        this.status = orderList.getStatus();
        MedicineQuantity medicineList = orderList.getMedicine();
        this.medicineQuantityId = medicineList.getId();
        this.medicineQuantity = medicineList.getQuantity();
        this.medicineName = medicineList.getMedicine().getName();
        this.dueDate = orderList.getDueDate().toString();
        if(orderList.getOffers().size() > 0 && orderList.getOffers() != null){
            for(Offer o : orderList.getOffers()){
                this.offers.add(new OfferDTO(o));
            }
        }

    }
}
