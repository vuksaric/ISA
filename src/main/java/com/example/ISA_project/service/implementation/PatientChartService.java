package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.PatientChart;
import com.example.ISA_project.model.dto.MedicineAllergyDTO;
import com.example.ISA_project.repository.MedicineRepository;
import com.example.ISA_project.repository.PatientChartRepository;
import com.example.ISA_project.service.IMedicineService;
import com.example.ISA_project.service.IPatientChartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PatientChartService implements IPatientChartService {

    private final PatientChartRepository patientChartRepository;
    private final MedicineRepository medicineRepository;

    public PatientChartService(PatientChartRepository patientChartRepository, MedicineRepository medicineRepository){
        this.patientChartRepository = patientChartRepository;
        this.medicineRepository = medicineRepository;
    }

    @Override
    public Set<MedicineAllergyDTO> getPatientsAllergies(int id) {
        List<MedicineAllergyDTO> allergies = new ArrayList<MedicineAllergyDTO>();
        PatientChart patientChart = patientChartRepository.findOneById(id);
        for(Medicine allergy : patientChart.getAllergies()){
            allergies.add(new MedicineAllergyDTO(allergy));
        }
        Set<MedicineAllergyDTO> uniqueAllergies = new HashSet<MedicineAllergyDTO>(allergies);
        return uniqueAllergies;
    }

   @Override
    public Set<MedicineAllergyDTO> addPatientAllergy(MedicineAllergyDTO medicineAllergyDTO, int id) {
            List<Medicine> medicines = medicineRepository.getAllByName(medicineAllergyDTO.getName());

            PatientChart patientChart = patientChartRepository.findOneById(id);
            patientChart.getAllergies().addAll(medicines);
            patientChartRepository.save(patientChart);

        return getPatientsAllergies(id);
    }
}
