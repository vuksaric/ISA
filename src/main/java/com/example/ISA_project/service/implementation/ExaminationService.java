package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.ExaminationDTO;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.ReportRequest;
import com.example.ISA_project.repository.ExaminationRepository;
import com.example.ISA_project.service.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ExaminationService implements IExaminationService {

    private  final ExaminationRepository examinationRepository;
    private final IPharmacyService pharmacyService;
    private final IReportService reportService;
    private final IMedicineService medicineService;
    private final IPatientService patientService;


    public ExaminationService(ExaminationRepository examinationRepository, IPharmacyService pharmacyService, IReportService reportService,
                              IMedicineService medicineService, IPatientService patientService)
    {
        this.examinationRepository=examinationRepository;
        this.pharmacyService = pharmacyService;
        this.reportService = reportService;
        this.medicineService = medicineService;
        this.patientService = patientService;
    }


    @Override
    public List<ExaminationDTO> findAllFree() {
        List<ExaminationDTO> examintaions = new ArrayList<ExaminationDTO>();
        for(Examination examination: examinationRepository.findAllFree()){
          examintaions.add(new ExaminationDTO((examination)));
        }
        return examintaions;
    }

    @Override
    public ExaminationDTO reserveExamination(int id) {
        Examination examination = examinationRepository.findExaminationById(id);
        examination.setFree(false);
        examinationRepository.save(examination);
        return new ExaminationDTO(examinationRepository.findExaminationById(id));
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
        return new ExaminationDTO(examinationRepository.save(examination));
    }

    @Override
    public List<Period> freePeriodsPatient(List<Period> periods, int id) {
        List<Examination> examinations = examinationRepository.findAllFutureByPatient(id);
        for(Examination examination : examinations)
        {
            for(Period period : periods)
            {
                if(examination.getDate().getStart_date().equals(period.getStart_date())) {
                    periods.remove(period);
                    break;
                }
            }
        }

        return periods;
    }

}
