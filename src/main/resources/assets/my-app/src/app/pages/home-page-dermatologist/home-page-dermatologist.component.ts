import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page-dermatologist',
  templateUrl: './home-page-dermatologist.component.html',
  styleUrls: ['./home-page-dermatologist.component.css']
})
export class HomePageDermatologistComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  profile()
  {
    this.router.navigate(['profilePharmacist']);
  }

  workSchedule()
  {
    this.router.navigate(['workScheduleDermatologist']);
  }

  previousConsultations()
  {
    this.router.navigate(['previousExaminations']);
  }

  vacation()
  {
    this.router.navigate(['vacationRequest']);
  }

  patientSearch()
  {
    this.router.navigate(['searchPatients']);
  }

}
