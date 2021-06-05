import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

import { Examination } from 'src/app/models/examination';
import { AuthService } from 'src/app/services/auth.service';
import { ExaminationService } from 'src/app/services/examination.service';

@Component({
  selector: 'app-view-future-examinations',
  templateUrl: './view-future-examinations.component.html',
  styleUrls: ['./view-future-examinations.component.css']
})
export class ViewFutureExaminationsComponent implements OnInit {
  dataFromToken : any;
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

  constructor( private examinationService : ExaminationService, private toastr: ToastrService,
    private authorizationService : AuthService) { }

  ngOnInit(): void {
    this.dataFromToken = this.authorizationService.getDataFromToken();

    this.examinationService.getFutureExaminationsByPatient(this.dataFromToken.id).subscribe((examinations: Examination[]) => {
      this.listOfData = examinations; console.log(examinations)});
  }

  cancel(event): void{
    var idAttr = event.currentTarget.id;
    this.examinationService.cancelExamination(idAttr).subscribe(data => {
       console.log(data);
       this.toastr.success("You have successfully canceled your examination!");
      });
    location.reload();
  }

 
 

}
