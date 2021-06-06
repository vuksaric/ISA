package com.example.ISA_project.model;

import com.example.ISA_project.model.dto.MedicineOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderList {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //private OrderStatus status;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="OrderList_ID")
    private List<MedicineQuantity> medicines;
    private OrderStatus status;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MedicineQuantity medicine;
    private LocalDateTime dueDate;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="OrderList_ID")
    private List<Offer> offers;
    private int pharmacyId;
    private int adminId;

}
