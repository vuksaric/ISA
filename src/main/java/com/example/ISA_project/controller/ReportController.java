package com.example.ISA_project.controller;


import com.example.ISA_project.model.Report;
import com.example.ISA_project.model.dto.ReportRequest;
import com.example.ISA_project.service.IReportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final IReportService reportService;

    public ReportController(IReportService reportService)
    {
        this.reportService = reportService;
    }

    @PostMapping("/save")
    public Report login(@RequestBody ReportRequest request){
        return reportService.newReport(request);

    }

}
