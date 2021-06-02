package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.ERecipe;
import com.example.ISA_project.model.ERecipeStatus;
import com.example.ISA_project.model.MedicineQuantity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ERecipeDTO {
    private LocalDate date;
    private ERecipeStatus status;
    private List<MedicineQuantityDTO> medicines;

    public ERecipeDTO(ERecipe eRecipe){
        this.date = eRecipe.getDate();
        this.status = eRecipe.getERecipeStatus();
        List<MedicineQuantityDTO> list = new ArrayList<>();
        for(MedicineQuantity medicineQuantity : eRecipe.getMedicines()){
            list.add(new MedicineQuantityDTO(medicineQuantity));
        }
        this.medicines = list;
    }
}
