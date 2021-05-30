package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.FutureReservationDTO;
import com.example.ISA_project.model.dto.MedicineAllergyDTO;
import com.example.ISA_project.model.dto.ReviewObjectDTO;
import com.example.ISA_project.repository.MedicineRepository;
import com.example.ISA_project.repository.PatientChartRepository;
import com.example.ISA_project.service.IPatientChartService;
import com.example.ISA_project.service.IPatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PatientChartService implements IPatientChartService {

    private final PatientChartRepository patientChartRepository;
    private final MedicineRepository medicineRepository;
    private final IPatientService patientService;

    public PatientChartService(PatientChartRepository patientChartRepository, MedicineRepository medicineRepository, IPatientService patientService){
        this.patientChartRepository = patientChartRepository;
        this.medicineRepository = medicineRepository;
        this.patientService = patientService;
    }

    @Override
    public Set<MedicineAllergyDTO> getPatientsAllergies(int id) {
        List<MedicineAllergyDTO> allergies = new ArrayList<MedicineAllergyDTO>();
        PatientChart patientChart = patientChartRepository.findOneById(findPatientChartId(id));
        for(Medicine allergy : patientChart.getAllergies()){
            allergies.add(new MedicineAllergyDTO(allergy));
        }
        Set<MedicineAllergyDTO> uniqueAllergies = new HashSet<MedicineAllergyDTO>(allergies);
        return uniqueAllergies;
    }

   @Override
    public Set<MedicineAllergyDTO> addPatientAllergy(MedicineAllergyDTO medicineAllergyDTO, int id) {
            List<Medicine> medicines = medicineRepository.getAllByName(medicineAllergyDTO.getName());

            PatientChart patientChart = patientChartRepository.findOneById(findPatientChartId(id));
            patientChart.getAllergies().addAll(medicines);
            patientChartRepository.save(patientChart);

        return getPatientsAllergies(id);
    }

    @Override
    public int findPatientChartId(int idPatient) {
        return patientService.findOneById(idPatient).getPatientChart().getId();
    }

    @Override
    public List<FutureReservationDTO> removeReservation(int id, String serialNumber) {
        PatientChart patientChart = patientService.findOneById(id).getPatientChart();
        Reservation reservation = null;
        for(Reservation r : patientChart.getReservations()){
            if(r.getSerialNumber().equals(serialNumber)){
                reservation=r;
            }
        }
        patientChart.getReservations().remove(reservation);
        patientChartRepository.save(patientChart);
        return getPatientsFutureReservations(id);
    }

    @Override
    public List<FutureReservationDTO> getPatientsFutureReservations(int id) {
        int patientChartId = findPatientChartId(id);
        List<FutureReservationDTO> reservations = new ArrayList<>();
        for(Reservation r : patientChartRepository.findOneById(patientChartId).getReservations()){
            if(r.getDueDate().isAfter(LocalDateTime.now())) {
                reservations.add(new FutureReservationDTO(r));
            }
        }
        return reservations;
    }

    @Override
    public List<ReviewObjectDTO> getPatientDermatologist(int idPatient) {
        List<ReviewObjectDTO> reviewObjectDTOS = new ArrayList<>();
        PatientChart patientChart = patientChartRepository.findOneById(findPatientChartId(idPatient));
        for(Examination examination : patientChart.getPreviousExaminations()){
            ReviewObjectDTO r = new ReviewObjectDTO(examination.getDermatologist().getId(),
                    examination.getDermatologist().getFullName());
            if(!reviewObjectDTOS.contains(r))
                reviewObjectDTOS.add(r);
        }
        return reviewObjectDTOS;
    }

    @Override
    public List<ReviewObjectDTO> getPatientPharmacist(int idPatient) {
        List<ReviewObjectDTO> reviewObjectDTOS = new ArrayList<>();
        PatientChart patientChart = patientChartRepository.findOneById(findPatientChartId(idPatient));
        for(Consultation consultation : patientChart.getPreviousConsultations()){
            ReviewObjectDTO r = new ReviewObjectDTO(consultation.getPharmacist().getId(),
                    consultation.getPharmacist().getFullName());
            if(!reviewObjectDTOS.contains(r))
                reviewObjectDTOS.add(r);
        }
        return reviewObjectDTOS;
    }

    @Override
    public List<ReviewObjectDTO> getPatientMedicine(int idPatient) {
        List<ReviewObjectDTO> reviewObjectDTOS = new ArrayList<>();
        PatientChart patientChart = patientChartRepository.findOneById(findPatientChartId(idPatient));
        for(Reservation reservation : patientChart.getReservations()){
            if(reservation.isIssued()){
                ReviewObjectDTO r = new ReviewObjectDTO(reservation.getMedicine().getId(),
                        reservation.getMedicine().getMedicineInformation());
                if(!reviewObjectDTOS.contains(r))
                    reviewObjectDTOS.add(r);
            }

        }
        return reviewObjectDTOS;
    }

}
