import { Component, OnInit } from '@angular/core';
import { Medicine } from 'src/app/models/medicine';
import { Pharmacy } from 'src/app/models/pharmacy';
import { MedicineService } from 'src/app/services/medicine.service';
import { PharmacyService } from 'src/app/services/pharmacy.service';
import { differenceInCalendarDays } from 'date-fns';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { ReservationService } from 'src/app/services/reservation.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-medicine-reservation',
  templateUrl: './medicine-reservation.component.html',
  styleUrls: ['./medicine-reservation.component.css']
})
export class MedicineReservationComponent implements OnInit {
  validateForm!: FormGroup;
  searchValue: string;
  listOfData: Medicine[]=[];
  listOfPharmacies : Pharmacy[]=[];
  listOfOption : Array<{ label: string; value: number }> = [];
  isVisible = false;
  singleValue : string;
  date = null;
  idMedicine : number;
  bodyToken : any;

  constructor(private fb: FormBuilder, private medicineService : MedicineService,
     private pharmacyService : PharmacyService, public datepipe: DatePipe,
     private reservationService : ReservationService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.medicineService.getAllMedicine().subscribe(data=>{console.log(data); this.listOfData=data});
    this.validateForm = this.fb.group({
      pharmacy:[null, [Validators.required]], 
      date: [null, [Validators.required]],
    })

  }

  reserve(event): void{
    this.listOfOption=[];
    this.idMedicine = event.currentTarget.id;
    this.pharmacyService.getPharmaciesByMedicineQuantity(this.idMedicine).subscribe(data => {
      console.log(data); 
      this.listOfPharmacies = data;
      data.forEach(element=>{
        var p ="";
        p = p + element.name + '; ' + element.street + ', ' + element.town;
        this.listOfOption.push({ label: p, value: element.id });
      });
    });

    console.log(this.listOfOption);
    this.isVisible=true;
  }

  onChange(result: Date): void {
    this.date = result;
  }
  disabledDate = (current: Date): boolean => {
    return differenceInCalendarDays(current, Date.now()) < 0;
  };

  handleOk(): void {
    
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

    this.date = this.validateForm.value.date;
    this.singleValue = this.validateForm.value.pharmacy;

    if(this.validateForm.valid){
      let r = Math.random().toString(36).substring(7);
      const body = {
        dueDate : this.date,
        serialNumber : r,
        idPatient : 1,
        idMedicine : this.idMedicine,
        idPharmacy : this.singleValue
      }
      this.reservationService.makeReservation(body).subscribe(data=> console.log(data));
      console.log(this.singleValue + this.datepipe.transform(this.date, 'yyyy-MM-dd HH:mm:ss'));
      this.isVisible=false;
      this.toastr.success("You have successfully made a medicine reservation!");
    }

    
  }
 
  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
    location.reload();
  }
}
