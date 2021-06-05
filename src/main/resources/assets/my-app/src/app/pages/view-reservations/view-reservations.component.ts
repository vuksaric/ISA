import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';
import { PatientChartService } from 'src/app/services/patient-chart.service';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-view-reservations',
  templateUrl: './view-reservations.component.html',
  styleUrls: ['./view-reservations.component.css']
})
export class ViewReservationsComponent implements OnInit {
  listOfData = [];
  dataFromToken : any;

  constructor(private patientChartService : PatientChartService, private reservationService : ReservationService,
    private toastr: ToastrService, private authorizationService : AuthService) { }

  ngOnInit(): void {
    this.dataFromToken = this.authorizationService.getDataFromToken();

    this.patientChartService.getFutureReservations(this.dataFromToken.id).subscribe(data => {console.log(data); this.listOfData=data;});
  }

  cancel(item): void{
    this.patientChartService.removeReservation(this.dataFromToken.id, item.serialNumber).subscribe(data=>{
      console.log(data.serialNumber);
      this.reservationService.cancelReservation(item.serialNumber).subscribe(data=> {
        console.log(data);
        this.toastr.success("You have successfully canceled your reservation!"); 
      });
      setTimeout(function(){location.reload()}, 3000);
    });
     
  }
}
