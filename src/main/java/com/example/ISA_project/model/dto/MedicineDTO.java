package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Ingredient;
import com.example.ISA_project.model.IssuingMode;
import com.example.ISA_project.model.Medicine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDTO {
    private int id;
    private String name;
    private String type;
    private String shape;
    private List<Ingredient> ingredients;
    private String manifacturer;
    private IssuingMode issuingMode;
    private List<Medicine> replacements;
    private String notes;

    public MedicineDTO(Medicine m){
        this.id = m.getId();
        this.name = m.getName();
        this.type = m.getType();
        this.shape = m.getShape();
        this.ingredients = m.getIngredients();
        this.manifacturer = m.getManifacturer();
        this.issuingMode = m.getIssuingMode();
        this.replacements = m.getReplacements();
        this.notes = m.getNotes();
    }
}
