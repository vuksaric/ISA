package com.example.ISA_project.controller;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Period;
import com.example.ISA_project.model.dto.*;
import com.example.ISA_project.repository.PatientRepository;
import com.example.ISA_project.service.IExaminationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/examination")
public class ExaminationController {
    private final IExaminationService examinationService;
    public ExaminationController(IExaminationService examinationService ){
        this.examinationService = examinationService;
    }
    @GetMapping(value="/getFree")
    public ResponseEntity<List<ExaminationDTO>> getAllFree(){
        return new ResponseEntity<>(examinationService.findAllFree(), HttpStatus.OK);
    }

    @PutMapping(value = "/reserve/{id}/{patient}")
    public ResponseEntity<ExaminationDTO> reserveExamination(@PathVariable String id, @PathVariable String patient){
        int idExamination= Integer.parseInt(id);
        int idPatient= Integer.parseInt(patient);
        return new ResponseEntity<>(examinationService.reserveExamination(idExamination,idPatient), HttpStatus.OK);
    }

    @GetMapping(value="/getFuture/{id}")
    public ResponseEntity<List<ExaminationDTO>> getAllFutureByPatient(@PathVariable String id){
        int idPatient= Integer.parseInt(id);
        return new ResponseEntity<>(examinationService.findAllFutureByPatient(idPatient), HttpStatus.OK);
    }

    @PutMapping(value = "/cancel/{id}")
    public ResponseEntity<ExaminationDTO> cancelExamination(@PathVariable String id){
        int idExamination= Integer.parseInt(id);
        return new ResponseEntity<>(examinationService.cancelExamination(idExamination), HttpStatus.OK);
    }

    @GetMapping(value = "/getMedicines/{id}")
    public List<MedicineDTO> geMedicines(@PathVariable String id){
        int idPharmacist= Integer.parseInt(id);
        return examinationService.getMedicines(idPharmacist);
    }

    @PutMapping(value = "/prescribe")
    public boolean prescribeMedicine(@RequestBody PrescribeRequest request){

        int idExamination= Integer.parseInt(request.getIdConsultation());
        int idMedicine = Integer.parseInt(request.getIdMedicine());
        return examinationService.prescribeMedicine(idExamination,idMedicine);
    }

    @PostMapping("/replacements")
    public List<MedicineDTO> getReplacements(@RequestBody PrescribeRequest request){
        int idExamination = Integer.parseInt(request.getIdConsultation());
        int idMedicine = Integer.parseInt(request.getIdMedicine());
        return examinationService.getReplacements(idExamination,idMedicine);
    }

    @PostMapping("/finish")
    public ExaminationDTO finish(@RequestBody ReportRequest request){

        return examinationService.finish(request);
    }

    @GetMapping(value="/getFutureExams/{id}")
    public ResponseEntity<List<AppointmentDTO>> getFutureByPatient(@PathVariable String id){
        int idPatient= Integer.parseInt(id);
        return new ResponseEntity<>(examinationService.findFutureByPatient(idPatient), HttpStatus.OK);
    }

    @GetMapping(value="/getPreviousDermatologist/{id}")
    public ResponseEntity<List<PreviousAppointmentDTO>> getPreviousByDermatologist(@PathVariable String id){
        int idDermatologist = Integer.parseInt(id);
        return new ResponseEntity<>(examinationService.getAllPreviousByDermatologist(idDermatologist), HttpStatus.OK);
    }

    @PostMapping("/freePeriods")
    public List<Period> freePeriods(@RequestBody PeriodsRequest request){
        int idExamination = Integer.parseInt(request.getId());
        return examinationService.freePeriods(idExamination, request.getDate());
    }

    @PostMapping("/newDermatologist")
    public AppointmentDTO newConsultationPharmacist(@RequestBody AppointmentRequest request){

        return examinationService.newExaminationDermatologist(request);
    }

    @GetMapping("/examinationReport/{id}/{mode}")
    public ResponseEntity getReport(@PathVariable String id, @PathVariable String mode){
        int pharmacyId = Integer.parseInt(id);
        int mode2 = Integer.parseInt(mode);
        try{
            return new ResponseEntity(examinationService.examinationReport(pharmacyId,mode2),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
