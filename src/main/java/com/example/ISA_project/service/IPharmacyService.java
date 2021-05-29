package com.example.ISA_project.service;


import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.MedicineQuantity;
import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.PharmacyDTO;

import java.util.List;

public interface IPharmacyService {
    List<PharmacyDTO> findAll();
    Boolean registerPharmacy(Pharmacy pharmacy);
    boolean prescribeMedicine(int idPharmacy, int idMedicine);
    List<MedicineDTO> getReplacements(int idPharmacy, int idMedicine);
    boolean checkQuantity(int idPharmacy, int idMedicine);
    Pharmacy findOneById(int id);
    List<PharmacyDTO> findPharmacyByMedicineQuantity(int id);
    Pharmacy subtractMedicineQuantity(int idMedicine, int idPharmacy);
    Pharmacy addMedicineQuantity(int idMedicine, int idPharmacy);
    List<PharmacyDTO> subscribedPharmacies(int idPatient);
}
