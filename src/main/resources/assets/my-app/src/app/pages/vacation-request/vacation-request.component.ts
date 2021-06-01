import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PharmacistService } from 'src/app/services/pharmacist.service';
import { VacationRequestService } from 'src/app/services/vacation-request.service';
import { differenceInCalendarDays, setHours } from 'date-fns';

@Component({
  selector: 'app-vacation-request',
  templateUrl: './vacation-request.component.html',
  styleUrls: ['./vacation-request.component.css']
})
export class VacationRequestComponent implements OnInit {


  validateForm: FormGroup;
  today = new Date();
  name : String;
  type : String;
  disabledStartDate = (current: Date): boolean => {
    // Can not select days before today and today
    return differenceInCalendarDays(current, this.today) <= 0;
  };

  disabledEndDate = (current: Date): boolean => {
    // Can not select days before today and today
    return differenceInCalendarDays(current, this.dateStart) > 0;
  };

  constructor(private vacationRequestService : VacationRequestService, private fb: FormBuilder) { 
    this.validateForm = this.fb.group({
      dateStart: ['', [Validators.required]],
      dateEnd: ['', [Validators.required]],
      name: ['', [Validators.required]],
    })
  }

  ngOnInit(): void {

    this.type = "pharmacist";

  }

  dateStart = null;
  dateEnd = null;

  onChangeStart(result: Date): void {
    this.dateStart = result;
    console.log('onChange: ', result);

    this.disabledEndDate = (current: Date): boolean => {
      // Can not select days before today and today
      return differenceInCalendarDays(current, this.dateStart) < 0;
    };
  }

  onChangeEnd(result: Date): void {
    this.dateEnd = result;
    console.log('onChange: ', result);
  }



  submit() : void
  {

    for (const key in this.validateForm.controls) {
      this.validateForm.controls[key].markAsDirty();
      this.validateForm.controls[key].updateValueAndValidity();
    }

    const body = {
      start_date: this.dateStart,
      end_date: this.dateEnd,
      user_id: 1,
      pharmacy_name : this.name
    }

    console.log(body);
    if(this.validateForm.valid)
      this.vacationRequestService.sendVacationRequest(body).subscribe(data=>{console.log("usao")});
    else
      alert("All fields are required");
    
  }

  visibility() : String
  {
    if(this.type.toLowerCase() == "pharmacist")
    {
      return "hidden";
    }
      
    else
      return "text";
  }
 

}


