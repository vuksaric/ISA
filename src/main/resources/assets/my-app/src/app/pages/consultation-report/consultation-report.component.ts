import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-consultation-report',
  templateUrl: './consultation-report.component.html',
  styleUrls: ['./consultation-report.component.css']
})
export class ConsultationReportComponent implements OnInit {

  medicines : any[];
  alternatives : any[];
  searchValue : String;
  days : number;
  id : String;
  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.id= this.activatedRoute.snapshot.paramMap.get('id');
  }

  specification() : void{

  }

  prescribe() : void{

  }

}
