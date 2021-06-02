package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Ingredient;
import com.example.ISA_project.model.IssuingMode;
import com.example.ISA_project.model.Medicine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineIngredientsDTO {
    private String name;
    private String type;
    private String shape;
    private List<String> ingredients;
    private String manufacturer;
    private IssuingMode issuingMode;
    private List<Integer> replacements;
    private String notes;
    private int therapyPerDay;

    public Medicine mapMedicineIngredientsDTOtoMedicine(MedicineIngredientsDTO mi){
        Medicine m = new Medicine();
        m.setName(mi.name);
        m.setType(mi.type);
        m.setShape(mi.shape);
        List<Ingredient> list = new ArrayList<>();
        for(String ingredient : mi.ingredients){
            Ingredient ing = new Ingredient();
            ing.setName(ingredient);
            list.add(ing);
        }
        m.setIngredients(list);
        m.setIssuingMode(mi.issuingMode);
        m.setManufacturer(mi.manufacturer);
        m.setNotes(mi.notes);
        m.setReplacements(mi.replacements);
        m.setTherapyPerDay(mi.therapyPerDay);
        return m;
    }
}
