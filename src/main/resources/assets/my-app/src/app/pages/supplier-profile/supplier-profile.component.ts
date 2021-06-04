import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { differenceInCalendarDays } from 'date-fns';

@Component({
  selector: 'app-supplier-profile',
  templateUrl: './supplier-profile.component.html',
  styleUrls: ['./supplier-profile.component.css']
})
export class SupplierProfileComponent implements OnInit {

  selectedValuePhonePrefix = "+381";
  today = new Date();
  validateForm!: FormGroup;
  name: string;
  lastname : string;
  email : string;
  street : string;
  town : string;
  state : string;
  phone : string;

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

    this.name = this.validateForm.value.name;
    this.lastname = this.validateForm.value.surname;
    this.email = this.validateForm.value.email;
    this.street = this.validateForm.value.street;
    this.town = this.validateForm.value.town;
    this.state = this.validateForm.value.state;
    this.phone = this.validateForm.value.phone;

    const address ={
      street : this.street,
      town : this.town,
      state : this.state,
    }

    const body = {
      name: this.name,
      surname: this.lastname,
      email : this.email,
      address: address,
      phone : this.selectedValuePhonePrefix + this.phone,
    }
    if(this.validateForm.valid){
      //this.authservice.registration(body).subscribe(data => { console.log(data) })
    }
  }

  disabledDate = (current: Date): boolean => {
    return differenceInCalendarDays(current, this.today) > 0;
  };

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    
    this.validateForm = this.fb.group({
      name: ["Srdjan", [Validators.required]],
      surname: [null, [Validators.required]],
      email: [null, [Validators.email, Validators.required]],
      street : [null, [Validators.required]],
      town : [null, [Validators.required]],
      state : [null, [Validators.required]],
      phoneNumberPrefix: ['+381'],
      phone: [null, [Validators.required]]
    });
  }

}
