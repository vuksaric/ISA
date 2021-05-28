package com.example.ISA_project.service;

import com.example.ISA_project.model.Report;
import com.example.ISA_project.model.dto.ReportRequest;

public interface IReportService {

    Report newReport(ReportRequest request);
}
