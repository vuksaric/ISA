package com.example.ISA_project.controller;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Period;
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
    public List<PreviousAppointmentDTO> getPreviousByPharmacist(@PathVariable String id){
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
        return consultationService.prescribeMedicine(idConsultation,idMedicine);
    }

    @PostMapping("/replacements")
    public List<MedicineDTO> getReplacements(@RequestBody PrescribeRequest request){
        int idConsultation = Integer.parseInt(request.getIdConsultation());
        int idMedicine = Integer.parseInt(request.getIdMedicine());
        return consultationService.getReplacements(idConsultation,idMedicine);
    }

    @PostMapping("/finish")
    public Consultation finish(@RequestBody ReportRequest request){

        return consultationService.finish(request);
    }

    @PostMapping("/freePeriods")
    public List<Period> freePeriods(@RequestBody PeriodsRequest request){
        int idConsultation = Integer.parseInt(request.getId());
        return consultationService.freePeriods(idConsultation, request.getDate());
    }

    @GetMapping(value = "/getFutureByPatient/{id}/{pharmacist}")
    public List<AppointmentDTO> getFutureByPatient(@PathVariable String id, @PathVariable String pharmacist){
        int idPatient= Integer.parseInt(id);
        int idPharmacist= Integer.parseInt(pharmacist);
        return consultationService.getFutureByPatient(idPatient, idPharmacist);
    }

    @PostMapping("/newPharmacist")
    public AppointmentDTO newConsultationPharmacist(@RequestBody AppointmentRequest request){

        return consultationService.newConsultationPharmacist(request);
    }

    @PutMapping(value = "/addPoint")
    public void addPenaltyPoint(@RequestBody String id) {

        int idConsultation = Integer.parseInt(id);
        consultationService.addPenaltyPoint(idConsultation);
    }
    @PostMapping("/newPatient")
    public ConsultationDTO newConsultationPatient(@RequestBody ConsultationRequest request){
        return consultationService.newConsultationPatient(request);
    }

    @PutMapping(value = "/cancel/{id}")
    public void cancelConsultation(@PathVariable String id){
        int pharmacyId = Integer.parseInt(id);
        consultationService.cancelConsultationPatient(pharmacyId);
    }
}
