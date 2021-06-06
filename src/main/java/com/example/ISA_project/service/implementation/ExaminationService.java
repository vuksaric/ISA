package com.example.ISA_project.service.implementation;


import com.example.ISA_project.model.*;
import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.dto.*;
import com.example.ISA_project.repository.ExaminationRepository;
import com.example.ISA_project.service.*;
import com.example.ISA_project.service.IExaminationService;
import com.example.ISA_project.service.IPatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ExaminationService implements IExaminationService {

    private  final ExaminationRepository examinationRepository;
    private final IPharmacyService pharmacyService;
    private final IReportService reportService;
    private final IMedicineService medicineService;
    private final IPatientService patientService;
    private final IBillService billService;
    private final IDermatologistService dermatologistService;
    private final IPatientChartService patientChartService;
    private final IEmailService emailService;


    public ExaminationService(ExaminationRepository examinationRepository, IPharmacyService pharmacyService, IReportService reportService,
                              IMedicineService medicineService, IPatientService patientService, IBillService billService,
                              IDermatologistService dermatologistService, IPatientChartService patientChartService, IEmailService emailService)
    {
        this.examinationRepository=examinationRepository;
        this.pharmacyService = pharmacyService;
        this.reportService = reportService;
        this.medicineService = medicineService;
        this.patientService = patientService;
        this.billService = billService;
        this.dermatologistService = dermatologistService;
        this.patientChartService = patientChartService;
        this.emailService = emailService;
    }


    @Override
    public List<ExaminationDTO> findAllFree() {
        List<ExaminationDTO> examintaions = new ArrayList<ExaminationDTO>();
        for(Examination examination: examinationRepository.findAllFree()){
            if(examination.getDate().getStart_date().isAfter(LocalDateTime.now())){
                examintaions.add(new ExaminationDTO((examination)));
            }
        }
        return examintaions;
    }

    @Override
    public List<ExaminationDTO> findAllFutureByPatient(int id) {
        List<ExaminationDTO> examintaions = new ArrayList<ExaminationDTO>();
        for(Examination examination: examinationRepository.findAllFutureByPatient(id)){
            examintaions.add(new ExaminationDTO((examination)));
        }
        return examintaions;
    }

    @Override
    public ExaminationDTO reserveExamination(int id, int idPatient) {
        Examination examination = examinationRepository.findExaminationById(id);
        examination.setFree(false);
        Patient patient = patientService.findOneById(idPatient);
        examination.setPatient(patient);
        examinationRepository.save(examination);
        patientService.saveFutureExamination(examination);
        emailService.scheduleExaminationEmail(examination);

        return new ExaminationDTO(examinationRepository.findExaminationById(id));
    }

    @Override
    public ExaminationDTO cancelExamination(int id) {
        Examination examination = examinationRepository.findExaminationById(id);
        examination.setFree(true);
        patientService.cancelExamination(examination);
        examination.setPatient(null);
        examinationRepository.save(examination);


        return new ExaminationDTO(examinationRepository.findExaminationById(id));
    }

    @Override
    public List<AppointmentDTO> findFutureByPatient(int id, int dermatologist) {
        List<Examination> examinations = examinationRepository.findAllFutureByPatientAndDermatologist(id, dermatologist);
        List<AppointmentDTO> result = new ArrayList<>();
        for(Examination examination : examinations)
            result.add(new AppointmentDTO(examination));
        return result;
    }

    @Override
    public List<PreviousAppointmentDTO> getAllPreviousByDermatologist(int id) {
        List<Examination> examinations = examinationRepository.findAllPreviousByDermatologist(id);
        List<PreviousAppointmentDTO> result = new ArrayList<>();

        for(Examination examination : examinations)
        {
            if(examination.isDone())
                result.add(new PreviousAppointmentDTO(examination));
        }
        return result;
    }

    @Override
    public boolean checkVacation(CheckVacationRequest request) {
        Examination examination = examinationRepository.findExaminationById(request.getPharmacyId());
        request.setPharmacyId(examination.getPharmacy().getId());
        return dermatologistService.checkVacation(request);
    }


    @Override
    public List<MedicineDTO> getMedicines(int id) {
        Examination examination = examinationRepository.findExaminationById(id);
        List<MedicineDTO> medicines = new ArrayList<>();
        boolean check = true;

        for(MedicineQuantity medicineQuantity : examination.getPharmacy().getMedicines())
        {
            check = true;
            for(Medicine medicine : examination.getPatient().getPatientChart().getAllergies())
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
    public boolean prescribeMedicine(int idExamination, int idMedicine) {
        Examination examination = examinationRepository.findExaminationById(idExamination);
        return pharmacyService.checkQuantity(examination.getPharmacy().getId(),idMedicine);
    }

    @Override
    public List<MedicineDTO> getReplacements(int idExamination, int idMedicine) {
        Examination examination = examinationRepository.findExaminationById(idExamination);
        return pharmacyService.getReplacements(examination.getPharmacy().getId(),idMedicine);
    }

    @Override
    public ExaminationDTO finish(ReportRequest request) {
        Examination examination = examinationRepository.findExaminationById(Integer.parseInt(request.getId()));
        pharmacyService.prescribeMedicine(examination.getPharmacy().getId(),request.getMedicine().getId());
        Report report = reportService.newReport(request);
        patientService.saveExamination(examination);
        examination.setDiagnosis(request.getDiagnosis());
        examination.setReport(report);
        examination.setDone(true);
        billService.newBill(report.getTherapy().getMedicine(),examination.getPharmacy());
        return new ExaminationDTO(examinationRepository.save(examination));
    }


    @Override
    public List<Period> freePeriods(int id, LocalDate date) {
        Examination examination = examinationRepository.findExaminationById(id);
        List<Period> periods = dermatologistService.freePeriods(examination.getDermatologist().getId(),date,examination.getPharmacy().getId());
        periods = patientChartService.freePeriods(periods,examination.getPatient().getPatientChart().getId());
        return periods;
    }

    @Override
    public AppointmentDTO newExaminationDermatologist(AppointmentRequest request) {
        Examination examination = examinationRepository.findExaminationById(request.getConsultationId());
        List<Examination> examinations = examinationRepository.findAll();
        Examination newExamination = null;
        for(Examination e : examinations)
        {
            if(e.getDate().getStart_date().equals(request.getPeriod().getStart_date()))
            {
                e.setFree(false);
                e.setPatient(examination.getPatient());
                e.setDermatologist(examination.getDermatologist());
                newExamination = e;
                break;
            }
        }

        if(newExamination == null)
             newExamination = new Examination(request.getPeriod(),examination.getPharmacy(),examination.getDermatologist(),examination.getPatient(),examination.getPrice());

        Examination result = examinationRepository.save(newExamination);
        dermatologistService.addNewExamination(result);
        patientService.saveFutureExamination(result);
        emailService.newExamination(result);
        return new AppointmentDTO(result);
    }

    @Override
    public void addPenaltyPoint(int id) {
        Examination examination = examinationRepository.findExaminationById(id);
        examination.setDone(true);
        patientService.addPenaltyPoint(examination.getPatient().getId());
    }


}
