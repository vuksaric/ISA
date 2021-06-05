import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Profile } from 'src/app/models/profile';
import { AuthService } from 'src/app/services/auth.service';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-profile-patient-doctor',
  templateUrl: './profile-patient-doctor.component.html',
  styleUrls: ['./profile-patient-doctor.component.css']
})
export class ProfilePatientDoctorComponent implements OnInit {


  profile: Profile;
  data1: any;
  data: any
  id : String;
  dataToken : any;
  type : String;
  visibleCons : boolean;
  visibleExam : boolean;

  constructor(private patientService: PatientService, private activatedRoute: ActivatedRoute, private router: Router, private authorizationService : AuthService ) {}

  ngOnInit(): void {

    this.dataToken = this.authorizationService.getDataFromToken();
    this.type = this.dataToken.type;
    console.log(this.type);
    if(this.type == "Pharmacist")
    {
      this.visibleExam = true;
      this.visibleCons = false;
    }
    else
    {
      this.visibleExam = false;
      this.visibleCons = true;
    }
    this.id= this.activatedRoute.snapshot.paramMap.get('id');
    this.patientService.getProfile(this.id).subscribe(data => { console.log(data); 
      this.data1 = [
      { title: 'Email', description: data.email },
      { title: 'Full name', description: data.name + ' ' + data.surname},
      { title: 'Date of birth', description: data.date[2] + '.' + data.date[1] + '.' + data.date[0]},
      { title: 'Adress', description: data.address + ', ' + data.town + ', ' + data.state },
      { title: 'Phone number', description: data.phone }
    ] });
    

     
  
  }

  
  exams() {

    this.router.navigate(['homePageDermatologist/viewPreviousExaminatiosProfile/' + this.id]);

  }

  cons(){

    this.router.navigate(['homePagePharmacist/viewPreviousConsultationsProfile/' + this.id]);

  }


}
