package com.example.ISA_project.service;


import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.MedicineQuantity;
import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.dto.*;

import java.util.List;

public interface IPharmacyService {
    List<PharmacyDTO> findAll();
    List<PharmacyDTO> findAllDermatologist(int id);
    Boolean registerPharmacy(Pharmacy pharmacy);
    boolean prescribeMedicine(int idPharmacy, int idMedicine);
    List<MedicineDTO> getReplacements(int idPharmacy, int idMedicine);
    boolean checkQuantity(int idPharmacy, int idMedicine);
    Pharmacy findOneById(int id);
    PharmacyDTO findOneByIdDTO(int id);
    List<PharmacyDTO> findPharmacyByMedicineQuantity(int id);
    Pharmacy subtractMedicineQuantity(int idMedicine, int idPharmacy);
    Pharmacy addMedicineQuantity(int idMedicine, int idPharmacy);
    List<PharmacyDTO> subscribedPharmacies(int idPatient);
    Pharmacy getByName(String name);
    List<PharmacistDTO> getAllPharmacists(int id);
    List<DermatologistDTO> getAllDermatologists(int id);
    List<MedicineQuantityDTO> getAllMedicines(int id);
    float getMark(int id);
    void newMedicineQuantity(int medicineId, int pharmacyId);
    List<MedicineQuantityDTO> removeMedicineQuantity(int medicineId, int pharmacyId);
    List<MedicineQuantityDTO> search(String input, int pharmacyId);

}
