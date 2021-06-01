package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.ExaminationDTO;
import com.example.ISA_project.model.dto.FutureReservationDTO;
import com.example.ISA_project.model.dto.MedicineAllergyDTO;
import com.example.ISA_project.model.dto.ReviewObjectDTO;
import com.example.ISA_project.repository.MedicineRepository;
import com.example.ISA_project.repository.PatientChartRepository;
import com.example.ISA_project.service.IMedicineService;
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
    private final IMedicineService medicineService;
    private final IPatientService patientService;

    public PatientChartService(PatientChartRepository patientChartRepository, IMedicineService medicineService, IPatientService patientService){
        this.patientChartRepository = patientChartRepository;
        this.medicineService = medicineService;
        this.patientService = patientService;
    }

    @Override
    public Set<MedicineAllergyDTO> getPatientsAllergies(int id) {
        List<MedicineAllergyDTO> allergies = new ArrayList<MedicineAllergyDTO>();
        PatientChart patientChart = patientChartRepository.findOneById(findPatientChartId(id));
        for(Medicine allergy : patientChart.getAllergies()){
            allergies.add(new MedicineAllergyDTO(allergy));
        }

        return  new HashSet<MedicineAllergyDTO>(allergies);
    }

   @Override
    public Set<MedicineAllergyDTO> addPatientAllergy(MedicineAllergyDTO medicineAllergyDTO, int id) {
            List<Medicine> medicines = medicineService.findMedicinesByName(medicineAllergyDTO.getName());

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

    @Override
    public Set<ReviewObjectDTO> getPatientPharmacy(int idPatient) {
        List<ReviewObjectDTO> reviewObjectDTOS = new ArrayList<>();
        PatientChart patientChart = patientChartRepository.findOneById(findPatientChartId(idPatient));

        reviewObjectDTOS.addAll(findReservationPharmacy(patientChart.getReservations()));
        reviewObjectDTOS.addAll(findExaminationPharmacy(patientChart.getPreviousExaminations()));
        reviewObjectDTOS.addAll(findConsultationPharmacy(patientChart.getPreviousConsultations()));

        return new HashSet<>(reviewObjectDTOS);
    }

    @Override
    public List<ExaminationDTO> getPatientPreviousExaminations(int id) {
        List<ExaminationDTO> examinationDTOS = new ArrayList<>();
        PatientChart patientChart = patientChartRepository.findOneById(findPatientChartId(id));

        for(Examination examination : patientChart.getPreviousExaminations()){
            examinationDTOS.add(new ExaminationDTO(examination));
        }

        return examinationDTOS;
    }

    private Set<ReviewObjectDTO> findReservationPharmacy(List<Reservation> reservations){
        List<ReviewObjectDTO> reviewObjectDTOS = new ArrayList<>();
        for(Reservation reservation : reservations){
            if(reservation.isIssued()){
                ReviewObjectDTO r = new ReviewObjectDTO(reservation.getPharmacy().getId(),
                        reservation.getPharmacy().getName() + "; " + reservation.getPharmacy().getAddress().getFullAdress());
                reviewObjectDTOS.add(r);
            }
        }
        return new HashSet<>(reviewObjectDTOS);
    }

    private Set<ReviewObjectDTO> findExaminationPharmacy(List<Examination> examinations){
        List<ReviewObjectDTO> reviewObjectDTOS = new ArrayList<>();
        for(Examination examination : examinations){
            ReviewObjectDTO r = new ReviewObjectDTO(examination.getPharmacy().getId(),
                    examination.getPharmacy().getName() + "; " + examination.getPharmacy().getAddress().getFullAdress());
            reviewObjectDTOS.add(r);
        }
        return new HashSet<>(reviewObjectDTOS);
    }

    private Set<ReviewObjectDTO> findConsultationPharmacy(List<Consultation> consultations){
        List<ReviewObjectDTO> reviewObjectDTOS = new ArrayList<>();
        for(Consultation consultation : consultations){
            ReviewObjectDTO r = new ReviewObjectDTO(consultation.getPharmacy().getId(),
                    consultation.getPharmacy().getName() + "; " + consultation.getPharmacy().getAddress().getFullAdress());
            reviewObjectDTOS.add(r);
        }
        return new HashSet<>(reviewObjectDTOS);
    }

}
