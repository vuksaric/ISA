import { Component, Inject, OnInit } from '@angular/core';
import { PharmacistService } from 'src/app/services/pharmacist.service';
import { ReservationService } from 'src/app/services/reservation.service';
import { DOCUMENT } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';

interface Reservation {
  serial_number: string;
  due_date: Date;
  medicine: string;
  patient: string
}
@Component({
  selector: 'app-issuing-medicine',
  templateUrl: './issuing-medicine.component.html',
  styleUrls: ['./issuing-medicine.component.css']
})

export class IssuingMedicineComponent implements OnInit {
  searchValue = '';
  listOfDisplayData : any[];
  listOfData: Reservation[] = [
  ]
  dataToken : any;
  id : number;
  constructor(private pharmacistService: PharmacistService, private reservationService: ReservationService, @Inject(DOCUMENT) private _document: Document, private authorizationService : AuthService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.dataToken = this.authorizationService.getDataFromToken();
    this.id = this.dataToken.id;
    this.pharmacistService.getReservations(this.id).subscribe(data => { console.log(data); 
      this.listOfData = data;
    });

  }

  issue(serial_number : string) : void{
    
    this.reservationService.issue(serial_number).subscribe(data => { console.log(data); 
      console.log(data);
    });

    this._document.defaultView.location.reload();
  }

  search() : void {
    this.listOfDisplayData = this.listOfData.filter((item: Reservation) => item.serial_number.toLowerCase() == (this.searchValue.toLowerCase()));
    if(this.listOfDisplayData.length == 0)
    this.toastr.warning("Reservation doesn't exist or is expired");

   
  }

}
