import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';
import { ConsultationService } from 'src/app/services/consultation.service';
import { PatientChartService } from 'src/app/services/patient-chart.service';

@Component({
  selector: 'app-view-future-consultations',
  templateUrl: './view-future-consultations.component.html',
  styleUrls: ['./view-future-consultations.component.css']
})
export class ViewFutureConsultationsComponent implements OnInit {
  dataFromToken : any;
  listOfData  = [];
  listOfColumn = [
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
  constructor(private patientChartService : PatientChartService, private consultationService: ConsultationService,
    private toastr: ToastrService, private authorizationService : AuthService) { }

  ngOnInit(): void {
    this.dataFromToken = this.authorizationService.getDataFromToken();

    this.patientChartService.getUpcomingConsultationsByPatient(this.dataFromToken.id).subscribe(data => {
      this.listOfData = data; console.log(data);});
  }

  cancel(event): void{
    var idAttr = event.currentTarget.id;
    this.consultationService.cancelConsultation(idAttr).subscribe(data=>{
      console.log(data);
      this.toastr.success("You have successfully canceled your consultation!");
    });
    location.reload();
  }

}
