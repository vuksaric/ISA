import { Component, OnInit } from '@angular/core';

import { Examination } from 'src/app/models/examination';
import { ExaminationService } from 'src/app/services/examination.service';

@Component({
  selector: 'app-view-future-examinations',
  templateUrl: './view-future-examinations.component.html',
  styleUrls: ['./view-future-examinations.component.css']
})
export class ViewFutureExaminationsComponent implements OnInit {

  listOfData : Examination[] = [];
  listOfColumn = [
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

  constructor( private examinationService : ExaminationService) { }

  ngOnInit(): void {
    this.examinationService.getFutureExaminationsByPatient(1).subscribe((examinations: Examination[]) => {
      this.listOfData = examinations; console.log(examinations)});
  }

  cancel(event): void{
    var idAttr = event.currentTarget.id;
    this.examinationService.cancelExamination(idAttr).subscribe(data => { console.log(data) });
    location.reload();
  }

 
 

}
