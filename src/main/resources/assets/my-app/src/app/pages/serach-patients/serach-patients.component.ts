import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { ConsultationService } from 'src/app/services/consultation.service';
import { ExaminationService } from 'src/app/services/examination.service';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-serach-patients',
  templateUrl: './serach-patients.component.html',
  styleUrls: ['./serach-patients.component.css']
})
export class SerachPatientsComponent implements OnInit {

  constructor(private router: Router, private patientService: PatientService, private consultationService: ConsultationService, private examinationService: ExaminationService, private authorizationService : AuthService) { }

  names : String[];
  listOfDisplayData : String[];
  searchValue : String;
  listConsultations : any[];
  isVisible : boolean;
  type : String;
  data: any;
  dataToken : any;

  ngOnInit(): void {

    this.dataToken = this.authorizationService.getDataFromToken();
    this.type = this.dataToken.type;
    console.log(this.type);
    this.patientService.getNames().subscribe(data => { console.log(data); 
      this.names = data;
      this.listOfDisplayData = this.names;
    });

    this.isVisible = false;
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
    this.router.navigate(['homePagePharmacist/consultationReport/' + id]);
    else
    this.router.navigate(['homePageDermatologist/examinationReport/' + id]);
    
  }

  appointmentType() : String
  {
    if(this.type.toLowerCase() == "pharmacist")
      return "Consultations"
    else
      return "Examinations"
  }

  viewProfile(id : number) : void
  {
    this.router.navigate(['homePagePharmacist/patientProfileDoctor/' + id]);
  }

}
