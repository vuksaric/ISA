package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.PreviousConsultationDTO;
import com.example.ISA_project.model.dto.ReportRequest;
import com.example.ISA_project.repository.ConsultationRepository;
import com.example.ISA_project.repository.PharmacistRepository;
import com.example.ISA_project.service.*;
//import com.example.ISA_project.service.IReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultationService implements IConsultationService {

    private final ConsultationRepository consultationRepository;
    private final IPharmacyService pharmacyService;
    private final IReportService reportService;
    private final IMedicineService medicineService;
    private final IPatientService patientService;
    private final IPharmacistService pharmacistService;
    private final IExaminationService examinationService;

    public ConsultationService(ConsultationRepository consultationRepository, IPharmacyService pharmacyService, IReportService reportService,
                               IMedicineService medicineService,IPatientService patientService, IPharmacistService pharmacistService,
                               IExaminationService examinationService)
    {
        this.consultationRepository = consultationRepository;
        this.pharmacyService = pharmacyService;
        this.reportService = reportService;
        this.medicineService = medicineService;
        this.patientService = patientService;
        this.pharmacistService = pharmacistService;
        this.examinationService = examinationService;
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
    public boolean prescribeMedicine(int idConsultation, int idMedicine) {
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
        pharmacyService.prescribeMedicine(consultation.getPharmacy().getId(),request.getMedicine().getId());
        Report report = reportService.newReport(request);
        patientService.saveConsultation(consultation);
        consultation.setReport(report);
        consultation.setDone(true);
        return consultationRepository.save(consultation);
    }

    @Override
    public List<Period> freePeriodsPatient(List<Period> periods, int id) {
        List<Consultation> consultations = consultationRepository.findAllFutureByPatient(id);
        for(Consultation consultation : consultations)
        {
            for(Period period : periods)
            {
                if(consultation.getPeriod().getStart_date().equals(period.getStart_date())) {
                    periods.remove(period);
                    break;
                }
            }
        }

        return periods;
    }

    @Override
    public List<Period> freePeriods(int id, LocalDate date) {
        Consultation consultation = consultationRepository.findOneById(id);
        List<Period> periods = pharmacistService.freePeriods(consultation.getPharmacist().getId(),date);
        periods = freePeriodsPatient(periods,consultation.getPatient().getId());
        periods = examinationService.freePeriodsPatient(periods,consultation.getPatient().getId());
        return periods;
    }


}

