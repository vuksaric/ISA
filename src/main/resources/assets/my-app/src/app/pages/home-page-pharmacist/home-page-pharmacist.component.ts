import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-home-page-pharmacist',
  templateUrl: './home-page-pharmacist.component.html',
  styleUrls: ['./home-page-pharmacist.component.css']
})
export class HomePagePharmacistComponent implements OnInit {

  constructor(private router: Router, private authorizationService : AuthService, private toastr: ToastrService) { }

  isCollapsed = false;
  ngOnInit(): void {
    this.authorizationService.checkAuthPharmacist();
  }

  profile()
  {
    this.router.navigate(['homePagePharmacist/profilePharmacist']);
  }

  workSchedule()
  {
    this.router.navigate(['homePagePharmacist/workSchedule']);
  }

  previousConsultations()
  {
    this.router.navigate(['homePagePharmacist/previousConsultations']);
  }

  patientSearch()
  {
    this.router.navigate(['homePagePharmacist/searchPatients']);
  }

  vacation()
  {
    this.router.navigate(['homePagePharmacist/vacationRequest']);
  }

  issueMedicine()
  {
    this.router.navigate(['homePagePharmacist/issuingMedicine']);
  }

  logout()
  {
    localStorage.clear();
    this.router.navigate(['login']);
  }
}



