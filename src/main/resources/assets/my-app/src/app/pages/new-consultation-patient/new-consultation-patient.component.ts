import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { differenceInCalendarDays } from 'date-fns';

@Component({
  selector: 'app-new-consultation-patient',
  templateUrl: './new-consultation-patient.component.html',
  styleUrls: ['./new-consultation-patient.component.css']
})
export class NewConsultationPatientComponent implements OnInit {
  validateForm!: FormGroup;
  time = new Date();
  date = new Date();
  radioValue = 'A';
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      date:[null, [Validators.required]], 
      time: [null, [Validators.required]],
      duration: [null, [Validators.required]]
    })

    
    
  }

  disabledDate = (current: Date): boolean => {
    return differenceInCalendarDays(current, Date.now()) < 0;
  };
  disabledHours = (): number[] => {
    return [0,1,2,3,4,5,6,7,23];
  };
 
  handleSubmit(){
     for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

    this.date = this.validateForm.value.date;
    this.time = this.validateForm.value.time;
    this.radioValue = this.validateForm.value.duration;
  }
}
