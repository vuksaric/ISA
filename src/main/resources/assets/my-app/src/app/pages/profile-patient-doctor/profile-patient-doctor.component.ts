import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Profile } from 'src/app/models/profile';
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

  constructor(private patientService: PatientService, private activatedRoute: ActivatedRoute, private router: Router ) {}

  ngOnInit(): void {

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

    this.router.navigate(['viewPreviousExaminatiosProfile/' + this.id]);

  }

  cons(){

    this.router.navigate(['viewPreviousConsultationsProfile/' + this.id]);

  }

  back(){
    this.router.navigate(['previousConsultations']);
  }


}
