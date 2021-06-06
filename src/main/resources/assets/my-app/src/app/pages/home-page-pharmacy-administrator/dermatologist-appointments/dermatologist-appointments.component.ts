import { PharmacyService } from 'src/app/services/pharmacy.service';
import { Component, OnInit } from '@angular/core';
import { differenceInCalendarDays } from 'date-fns';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-dermatologist-appointments',
  templateUrl: './dermatologist-appointments.component.html',
  styleUrls: ['./dermatologist-appointments.component.css']
})
export class DermatologistAppointmentsComponent implements OnInit {

  dermatologistId: number;
  dermatologist: any[] = [];
  date: Date = null;
  today = new Date();
  price: number;
  freeAppointments: any[] = [];
  appointment: string;
  show = false;

  constructor(private pharmacyService: PharmacyService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.getDermatologists()
  }

  getDermatologists() {
    this.pharmacyService.getDermatologistsFromPharmacy('1').subscribe(data => {
      this.dermatologist = data;
    })
  }

  getAppointments() {
    if (this.date === null || this.dermatologistId === null) {
      this.toastr.error("Input fields can not be empty");
    } else {
      const body = {
        date: this.date.toISOString(),
        pharmacyId: '1',
        dermatologistId: this.dermatologistId
      }
      this.pharmacyService.getWorkingHoursOfDermatologist(body).subscribe(data => {
        this.freeAppointments = data;
        this.show = true;
      })
    }
  }

  reserve() {
    if (this.dermatologistId === undefined || this.date === null || this.date === undefined || this.appointment === null || this.appointment === undefined || this.price === undefined || this.price === null) {
      this.toastr.error("Input fields can not be empty");
      return;
    } else {
      var splitted = this.appointment.split(" ", 2);
      var start1 = splitted[0];
      var end1 = splitted[1];
      const body = {
        pharmacyId: '1',
        dermatologistId: this.dermatologistId,
        date: this.date.toISOString,
        start: start1.toString,
        end: end1.toString,
        price: this.price
      }
      this.pharmacyService.makeReservation(body).subscribe(data => {
        this.toastr.success("Successfully made reservation")
        this.show = false;
        this.date = null;
        this.dermatologistId = null;
      })
    }
  }

  onChange(result: Date): void {
    this.getAppointments();
    this.appointment = null;
  }

  disabledDate = (current: Date): boolean => {
    return differenceInCalendarDays(current, this.today) < 0;
  };

}
