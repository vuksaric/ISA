import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page-user',
  templateUrl: './home-page-user.component.html',
  styleUrls: ['./home-page-user.component.css']
})



export class HomePageUserComponent implements OnInit {

  
  constructor(private router: Router) { }

  ngOnInit(): void {
    
  }
  isCollapsed = false;

  patientProfile() {
    this.router.navigate(['homepage/patientProfile']);
  }
  newExamination(){
    this.router.navigate(['homepage/viewMadeExaminations']);
  }
  upcomingExamination(){
    this.router.navigate(['homepage/viewFutureExaminations']);
  }
  searchPharmacies(){
    this.router.navigate(['homepage/viewPharmacies']);
  }
  subsribedPharmacies(){
    this.router.navigate(['homepage/viewSubsribedPharmacies']);
  }
  reservedMedicine(){
    this.router.navigate(['homepage/viewReservations']);
  }
  newReservation(){
    this.router.navigate(['homepage/reserveMedicine']);
  }
  newConsultation(){
    this.router.navigate(['homepage/newConsultationPatient']);
  }
}

