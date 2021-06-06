import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-home-page-dermatologist',
  templateUrl: './home-page-dermatologist.component.html',
  styleUrls: ['./home-page-dermatologist.component.css']
})
export class HomePageDermatologistComponent implements OnInit {

  constructor(private router: Router, private authorizationService : AuthService, private toastr: ToastrService) { }

  isCollapsed = false;
  ngOnInit(): void {
    this.authorizationService.checkAuthDermatologist();
  }

  profile()
  {
    this.router.navigate(['homePageDermatologist/profilePharmacist']);
  }

  workSchedule()
  {
    this.router.navigate(['homePageDermatologist/workScheduleDermatologist']);
  }

  previousExaminations()
  {
    this.router.navigate(['homePageDermatologist/previousExaminations']);
  }

  vacation()
  {
    this.router.navigate(['homePageDermatologist/vacationRequest']);
  }

  patientSearch()
  {
    this.router.navigate(['homePageDermatologist/searchPatients']);
  }
  
  logout()
  {
    localStorage.clear();
    this.router.navigate(['login']);
  }
}
