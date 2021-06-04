import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { PatientChartService } from 'src/app/services/patient-chart.service';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-view-reservations',
  templateUrl: './view-reservations.component.html',
  styleUrls: ['./view-reservations.component.css']
})
export class ViewReservationsComponent implements OnInit {
  listOfData = [];
  

  constructor(private patientChartService : PatientChartService, private reservationService : ReservationService,
    private toastr: ToastrService) { }

  ngOnInit(): void {
    this.patientChartService.getFutureReservations(1).subscribe(data => {console.log(data); this.listOfData=data;});
  }

  cancel(item): void{
    this.patientChartService.removeReservation(1, item.serialNumber).subscribe(data=>{
      console.log(data.serialNumber);
      this.reservationService.cancelReservation(item.serialNumber).subscribe(data=> {
        console.log(data);
        this.toastr.success("You have successfully canceled your reservation!"); 
      });
      location.reload();
    });
     
  }
}
