import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Examination } from 'src/app/models/examination';
import { PatientChartService } from 'src/app/services/patient-chart.service';

@Component({
  selector: 'app-view-previous-examinations-profile',
  templateUrl: './view-previous-examinations-profile.component.html',
  styleUrls: ['./view-previous-examinations-profile.component.css']
})
export class ViewPreviousExaminationsProfileComponent implements OnInit {

  id : String;
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
  constructor(private patientChartService : PatientChartService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id= this.activatedRoute.snapshot.paramMap.get('id');
    this.patientChartService.getPreviousExaminationsByPatient(this.id).subscribe((examinations: Examination[]) => {
      this.listOfData = examinations;});
  }

  back(){
    this.router.navigate(['patientProfileDoctor/' + this.id]);
  }

}
