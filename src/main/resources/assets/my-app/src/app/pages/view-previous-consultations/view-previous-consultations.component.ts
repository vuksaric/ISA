import { Component, OnInit } from '@angular/core';
import { PatientChartService } from 'src/app/services/patient-chart.service';

@Component({
  selector: 'app-view-previous-consultations',
  templateUrl: './view-previous-consultations.component.html',
  styleUrls: ['./view-previous-consultations.component.css']
})
export class ViewPreviousConsultationsComponent implements OnInit {
  listOfData =[];

  listOfColumn = [
    {
      title: 'Consultation date',
      compare: (a: any, b: any) => Date.parse(a.date) - Date.parse(b.date),
      priority: 0
    },
    {
      title: 'Consultation time',
      compare: (a: any, b: any) =>  a.time.localeCompare(b.time),
      priority: 0
    },
    {
      title: 'Pharmacist mark',
      compare: (a: any, b: any) => a.mark - b.mark,
      priority: 2
    },
    {
      title: 'Price',
      compare: (a: any, b: any) => a.price - b.price,
      priority: 1
    }
  ];
  constructor(private patientChartService : PatientChartService) { }

  ngOnInit(): void {
    this.patientChartService.getPreviousConsultationsByPatient(1).subscribe(data => {
      this.listOfData = data; console.log(data);});
  }

}
