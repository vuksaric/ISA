import { Component, OnInit } from '@angular/core';
import { Medicine } from 'src/app/models/medicine';
import { PatientChartService } from 'src/app/services/patient-chart.service';

@Component({
  selector: 'app-view-erecipe-medicines',
  templateUrl: './view-erecipe-medicines.component.html',
  styleUrls: ['./view-erecipe-medicines.component.css']
})
export class ViewErecipeMedicinesComponent implements OnInit {
  searchValue: string;
  listOfData: Medicine[]=[];

  constructor(private patientChartService : PatientChartService) { }

  ngOnInit(): void {
    this.patientChartService.getPatientERecipeMedicines(1).subscribe((medicines : Medicine[])=>{
      this.listOfData=medicines;
    });
  }

}
