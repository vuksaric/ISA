import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Examination } from 'src/app/models/examination';
import { ExaminationService } from 'src/app/services/examination.service';

@Component({
  selector: 'app-view-made-examinations',
  templateUrl: './view-made-examinations.component.html',
  styleUrls: ['./view-made-examinations.component.css']
})
export class ViewMadeExaminationsComponent implements OnInit {
  
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

  constructor( private examinationService : ExaminationService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.examinationService.getFreeExaminations().subscribe((examinations: Examination[]) => {
      this.listOfData = examinations;});
  }

  reserve(event): void{
    var idAttr = event.currentTarget.id;
    var patient = 1;
    this.examinationService.reserveExamination(idAttr, patient).subscribe(data => { 
      console.log(data);
      this.toastr.success("You have successfully made a new examination!"); 
    });

    setTimeout(function(){location.reload()}, 3000);
  }
 
   

}
