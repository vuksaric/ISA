import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientChartService } from 'src/app/services/patient-chart.service';

@Component({
  selector: 'app-view-previous-consultations-profile',
  templateUrl: './view-previous-consultations-profile.component.html',
  styleUrls: ['./view-previous-consultations-profile.component.css']
})
export class ViewPreviousConsultationsProfileComponent implements OnInit {

  listOfData =[];
  id : String;

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
  constructor(private patientChartService : PatientChartService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id= this.activatedRoute.snapshot.paramMap.get('id');
    this.patientChartService.getPreviousConsultationsByPatient(this.id).subscribe(data => {
      this.listOfData = data; console.log(data);});
  }

  back(){
    this.router.navigate(['homePagePharmacist/patientProfileDoctor/' + this.id]);
  }

}
