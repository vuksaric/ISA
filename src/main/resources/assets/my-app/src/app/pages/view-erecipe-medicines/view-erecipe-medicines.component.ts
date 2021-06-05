import { Component, OnInit } from '@angular/core';
import { Medicine } from 'src/app/models/medicine';
import { AuthService } from 'src/app/services/auth.service';
import { PatientChartService } from 'src/app/services/patient-chart.service';

@Component({
  selector: 'app-view-erecipe-medicines',
  templateUrl: './view-erecipe-medicines.component.html',
  styleUrls: ['./view-erecipe-medicines.component.css']
})
export class ViewErecipeMedicinesComponent implements OnInit {
  searchValue: string;
  listOfData: Medicine[]=[];
  dataFromToken : any;


  constructor(private patientChartService : PatientChartService,  private authorizationService : AuthService) { }

  ngOnInit(): void {
    this.dataFromToken = this.authorizationService.getDataFromToken();

    this.patientChartService.getPatientERecipeMedicines(this.dataFromToken.id).subscribe((medicines : Medicine[])=>{
      this.listOfData=medicines;
    });
  }

}
