package com.example.ISA_project.controller;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Reservation;
import com.example.ISA_project.model.dto.*;
import com.example.ISA_project.service.IConsultationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    private final IConsultationService consultationService;

    public ConsultationController(IConsultationService consultationService)
    {
        this.consultationService = consultationService;
    }

    @GetMapping(value = "/getPreviousByPharmacist/{id}")
    public List<PreviousConsultationDTO> getPreviousByPharmacist(@PathVariable String id){
        int idPharmacist= Integer.parseInt(id);
        return consultationService.getPreviousByPharmacist(idPharmacist);
    }

    @GetMapping(value = "/getMedicines/{id}")
    public List<MedicineDTO> geMedicines(@PathVariable String id){
        int idPharmacist= Integer.parseInt(id);
        return consultationService.getMedicines(idPharmacist);
    }

    @PutMapping(value = "/prescribe")
    public boolean prescribeMedicine(@RequestBody PrescribeRequest request){

        int idConsultation= Integer.parseInt(request.getIdConsultation());
        int idMedicine = Integer.parseInt(request.getIdMedicine());
        return consultationService.PrescribeMedicine(idConsultation,idMedicine);
    }

    @PostMapping("/replacements")
    public List<MedicineDTO> login(@RequestBody PrescribeRequest request){
        int idConsultation = Integer.parseInt(request.getIdConsultation());
        int idMedicine = Integer.parseInt(request.getIdMedicine());
        return consultationService.getReplacements(idConsultation,idMedicine);
    }

    @PostMapping("/finish")
    public Consultation finish(@RequestBody ReportRequest request){
        return consultationService.finish(request);
    }
}
