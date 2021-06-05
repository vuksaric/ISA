import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-home-page-user',
  templateUrl: './home-page-user.component.html',
  styleUrls: ['./home-page-user.component.css']
})
export class HomePageUserComponent implements OnInit {
  userID : number;
  userType : string;
  bodyToken : any;
  constructor(private router: Router, private authorizationService : AuthService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.bodyToken=this.authorizationService.checkAuthPatient();
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
  previousExamination(){
    this.router.navigate(['homepage/viewPreviousExaminations']);
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
  viewReservation(){
    this.router.navigate(['homepage/viewReviews']);
  }
  futureConsultation(){
    this.router.navigate(['homepage/viewFutureConsultations']);
  }
  previousConsultation(){
    this.router.navigate(['homepage/viewPreviousConsultations']);
  }
  viewERecipes(){
    this.router.navigate(['homepage/viewERecipes']);
  }
  viewERecipeMedicines(){
    this.router.navigate(['homepage/viewERecipeMedicines']);
  }
  logout(){
    localStorage.clear();
    this.router.navigate(['login']);
  }
}

