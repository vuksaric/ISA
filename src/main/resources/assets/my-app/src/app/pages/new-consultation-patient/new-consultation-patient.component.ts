import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { differenceInCalendarDays } from 'date-fns';
import { DatePipe } from '@angular/common';
import { PharmacyService } from 'src/app/services/pharmacy.service';
import { Pharmacy } from 'src/app/models/pharmacy';
import { ConsultationService } from 'src/app/services/consultation.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-new-consultation-patient',
  templateUrl: './new-consultation-patient.component.html',
  styleUrls: ['./new-consultation-patient.component.css']
})
export class NewConsultationPatientComponent implements OnInit {
  validateForm!: FormGroup;
  time = new Date();
  date = new Date();
  dateTime : Date;
  listOfPharmacies = [];
  listOfPharmacists=[];
  isVisible=false;
  searchValue : string;
  pharmacyId : number;
  today : boolean;
  hour : number;

  listOfColumnPharmacist=[
    {
      title: 'Grade',
      compare: (a: any, b: any) => a.grade - b.grade,
      priority: 1,
     
    },
  ];

  listOfColumn = [
    {
      title: 'Grade',
      compare: (a: Pharmacy, b: Pharmacy) => a.price - b.price,
      priority: 1,
     
    },
    {
      title: 'Price',
      compare: (a: Pharmacy, b: Pharmacy) => a.mark - b.mark,
      priority: 2,
    }
  ];

  constructor(private fb: FormBuilder, private pharmacyService : PharmacyService, private datePipe : DatePipe,
    private consultationService : ConsultationService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      date:[null, [Validators.required]], 
      time: [null, [Validators.required]]
    })
  }

  onChange(result: Date): void {
    this.today=false;
    if(differenceInCalendarDays(result, Date.now()) == 0){
      this.today = true;
      this.hour = result.getHours();
      console.log(this.today+"  "+this.hour);
    }
  }

  disabledDate = (current: Date): boolean => {
    return differenceInCalendarDays(current, Date.now()) < 0;
  };

  disabledHours = (): number[] => {
    let hours =[];
    if(this.today){
      for(let i =0; i<20; i++){
        if(i<this.hour){
          hours.push(i);
        }
      }
      hours.push(22);
      hours.push(23);
      hours.push(this.hour);
    }
    else{
      hours=[0,1,2,3,4,5,6,7,22,23];
    }
    return hours;
  };
 
  handleSubmit(){
     for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

    this.date = this.validateForm.value.date;
    this.time = this.validateForm.value.time;
    this.dateTime = new Date(this.date.getFullYear(), this.date.getMonth(), this.date.getDate(), this.time.getHours(), this.time.getMinutes(), 0, 0);
    

    this.pharmacyService.getPharmaciesWithFreeAppointment(this.datePipe.transform(this.dateTime, 'yyyy-MM-dd HH:mm')).subscribe((pharmacies: Pharmacy[])=>{
      console.log(pharmacies); 
      this.listOfPharmacies=pharmacies
    });

  }

  pickPharmacy(data){
    this.isVisible = true;
    this.pharmacyId = data.id;

    this.pharmacyService.getAvailablePharmacistsByPharmacy(this.pharmacyId,this.datePipe.transform(this.dateTime, 'yyyy-MM-dd HH:mm')).subscribe(data=>{
      this.listOfPharmacists= data;
      console.log(data);
    });
  }

  pickPharmacist(data){
    const body = {
      dateTime: this.datePipe.transform(this.dateTime, 'yyyy-MM-dd HH:mm'),
      pharmacyId : this.pharmacyId,
      pharmacistId: data.id,
      patientId: 1
    }
    console.log(body)
    this.consultationService.newPatient(body).subscribe(data=>{
      console.log(data);
      this.toastr.success("You have successfully made a new consultation!");
      this.isVisible = false;
    });
  }

  handleOk(): void {
    console.log('Button ok clicked!');
    this.isVisible = false;
  }

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
  }
}
