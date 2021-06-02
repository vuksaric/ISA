import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConsultationService } from 'src/app/services/consultation.service';
import { ExaminationService } from 'src/app/services/examination.service';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-serach-patients',
  templateUrl: './serach-patients.component.html',
  styleUrls: ['./serach-patients.component.css']
})
export class SerachPatientsComponent implements OnInit {

  constructor(private router: Router, private patientService: PatientService, private consultationService: ConsultationService, private examinationService: ExaminationService) { }

  names : String[];
  listOfDisplayData : String[];
  searchValue : String;
  listConsultations : any[];
  isVisible : boolean;
  type : String;

  ngOnInit(): void {

    this.patientService.getNames().subscribe(data => { console.log(data); 
      this.names = data;
      this.listOfDisplayData = this.names;
    });

    this.isVisible = false;
    this.type = "Pharmacist";
  }

  search() : void
  {
    this.listOfDisplayData = this.names.filter((item: String) => item.toLowerCase().indexOf(this.searchValue.toLowerCase()) !== -1);
  }

  consultations(id : number) 
  {
    

    if(this.type.toLowerCase() == "pharmacist")
    {
      this.consultationService.getFutureByPatient(id).subscribe(data => { console.log(data); 
        this.listConsultations = data;
      });
    }
    else
    {
      this.examinationService.getFutureByPatient(id).subscribe(data => { console.log(data); 
        this.listConsultations = data;
      });
    }
    this.isVisible = true;
  }

  handleOk(): void {
    console.log('Button ok clicked!');
    this.isVisible = false;
  }

  startConsultation(id : number)
  {

    if(this.type.toLowerCase() == "pharmacist")
    this.router.navigate(['consultationReport/' + id]);
    else
    this.router.navigate(['examinationReport/' + id]);
    
  }

  appointmentType() : String
  {
    if(this.type.toLowerCase() == "pharmacist")
      return "Consultations"
    else
      return "Examinations"
  }

}
