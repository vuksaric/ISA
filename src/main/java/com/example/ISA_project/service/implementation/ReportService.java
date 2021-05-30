package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.Report;
import com.example.ISA_project.model.Therapy;
import com.example.ISA_project.model.dto.ReportRequest;
import com.example.ISA_project.repository.ReportRepository;
import com.example.ISA_project.service.IMedicineService;
import com.example.ISA_project.service.IReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {

    private final ReportRepository reportRepository;
    private final IMedicineService medicineService;

    public ReportService(ReportRepository reportRepository, IMedicineService medicineService)
    {
        this.reportRepository = reportRepository;
        this.medicineService = medicineService;
    }

    @Override
    public Report newReport(ReportRequest request) {

        //Therapy therapy = new Therapy(medicine,duration);
        //Report report = new Report(information,therapy);
        Medicine medicine = medicineService.findOneById(request.getMedicine().getId());
        Therapy therapy = new Therapy(medicine, request.getDuration());
        Report report = new Report(request.getInformation(), therapy);
        return reportRepository.save(report);
    }
}
