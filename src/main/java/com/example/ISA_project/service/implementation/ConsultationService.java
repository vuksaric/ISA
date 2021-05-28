package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.PreviousConsultationDTO;
import com.example.ISA_project.model.dto.ReportRequest;
import com.example.ISA_project.repository.ConsultationRepository;
import com.example.ISA_project.repository.PharmacistRepository;
import com.example.ISA_project.service.IConsultationService;
import com.example.ISA_project.service.IMedicineService;
import com.example.ISA_project.service.IPharmacyService;
//import com.example.ISA_project.service.IReportService;
import com.example.ISA_project.service.IReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultationService implements IConsultationService {

    private final ConsultationRepository consultationRepository;
    private final IPharmacyService pharmacyService;
    private final IReportService reportService;
    private final IMedicineService medicineService;

    public ConsultationService(ConsultationRepository consultationRepository, IPharmacyService pharmacyService, IReportService reportService, IMedicineService medicineService)
    {
        this.consultationRepository = consultationRepository;
        this.pharmacyService = pharmacyService;
        this.reportService = reportService;
        this.medicineService = medicineService;
    }

    @Override
    public List<PreviousConsultationDTO> getPreviousByPharmacist(int id) {

        List<Consultation> consultations= consultationRepository.findAll();
        List<PreviousConsultationDTO> result = new ArrayList<>();
        LocalDateTime today = LocalDateTime.now();
        for(Consultation consultation : consultations) {
            if (consultation.getPharmacist().getId() == id && consultation.getPeriod().getEnd_date().isBefore(today))
                result.add(new PreviousConsultationDTO(consultation.getPatient().getUser().getId(),consultation.getPatient().getUser().getName(), consultation.getPatient().getUser().getSurname(),
                        consultation.getPatient().getUser().getAddress().getFullAdress(), consultation.getPeriod().getStart_date(), consultation.getPharmacy().getName()));
        }
        return result;
    }

    @Override
    public List<MedicineDTO> getMedicines(int id) {

        Consultation consultation = consultationRepository.findOneById(id);
        List<MedicineDTO> medicines = new ArrayList<>();
        boolean check = true;

        for(MedicineQuantity medicineQuantity : consultation.getPharmacy().getMedicines())
        {
            check = true;
            for(Medicine medicine : consultation.getPatient().getPatientChart().getAllergies())
            {
                if(medicine.getId() == medicineQuantity.getMedicine().getId())
                    check = false;
            }

            if(check)
                medicines.add(new MedicineDTO(medicineQuantity.getMedicine()));
        }

        return medicines;
    }

    @Override
    public boolean PrescribeMedicine(int idConsultation, int idMedicine) {
        Consultation consultation = consultationRepository.findOneById(idConsultation);
        return pharmacyService.checkQuantity(consultation.getPharmacy().getId(),idMedicine);
    }

    @Override
    public List<MedicineDTO> getReplacements(int idConsultation, int idMedicine) {
        Consultation consultation = consultationRepository.findOneById(idConsultation);
        return pharmacyService.getReplacements(consultation.getPharmacy().getId(),idMedicine);
    }

    @Override
    public Consultation finish(ReportRequest request) {
        Consultation consultation = consultationRepository.findOneById(Integer.parseInt(request.getId()));
        //Medicine medicine = medicineService.getById(request.getMedicine().getId());
        //Therapy therapy = new Therapy(medicine, request.getDuration());
        //Report report = new Report(request.getInformation(), therapy);
        pharmacyService.prescribeMedicine(consultation.getPharmacy().getId(),request.getMedicine().getId());
        Report report = reportService.newReport(request);
        consultation.setReport(report);
        consultation.setDone(true);
        return consultationRepository.save(consultation);
    }


}

