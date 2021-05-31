import { Component, OnInit } from '@angular/core';
import { Examination } from 'src/app/models/examination';
import { PatientChartService } from 'src/app/services/patient-chart.service';

@Component({
  selector: 'app-view-previous-examinations',
  templateUrl: './view-previous-examinations.component.html',
  styleUrls: ['./view-previous-examinations.component.css']
})
export class ViewPreviousExaminationsComponent implements OnInit {
  listOfData : Examination[] = [];
  listOfColumn = [
    {
      title: 'Examination date',
      compare: (a: Examination, b: Examination) => Date.parse(a.date) - Date.parse(b.date),
      priority: 0
    },
    {
      title: 'Examination time',
      compare: (a: Examination, b: Examination) =>  a.time.localeCompare(b.time),
      priority: 0
    },
    {
      title: 'Dermatologist mark',
      compare: (a: Examination, b: Examination) => a.mark - b.mark,
      priority: 2
    },
    {
      title: 'Price',
      compare: (a: Examination, b: Examination) => a.price - b.price,
      priority: 1
    }
  ];
  constructor(private patientChartService : PatientChartService) { }

  ngOnInit(): void {
    this.patientChartService.getPreviousExaminationsByPatient(1).subscribe((examinations: Examination[]) => {
      this.listOfData = examinations;});
  }

}
