import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from './../../services/auth.service';
import { NzFormTooltipIcon } from 'ng-zorro-antd/form';
import { differenceInCalendarDays } from 'date-fns';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {

  selectedValuePhonePrefix = "+381";
  selectedValueGender = "Male";
  //selectedValueDate = null;
  today = new Date();

  validateForm!: FormGroup;
  name: string;
  lastname : string;
  email : string;
  password : string;
  street : string;
  town : string;
  state : string;
  phone : string;
  dateOfBirth : Date;

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

    this.name = this.validateForm.value.name;
    this.lastname = this.validateForm.value.surname;
    this.email = this.validateForm.value.email;
    this.password = this.validateForm.value.password;
    this.street = this.validateForm.value.street;
    this.town = this.validateForm.value.town;
    this.state = this.validateForm.value.state;
    this.phone = this.validateForm.value.phone;
    this.dateOfBirth = this.validateForm.value.dateOfBirth;

    const address ={
      street : this.street,
      town : this.town,
      state : this.state,
    }

    const body = {
      name: this.name,
      surname: this.lastname,
      email : this.email,
      password : this.password,
      address: address,
      phone : this.selectedValuePhonePrefix + this.phone,
      dateOfBirth: this.dateOfBirth,
      gender: this.selectedValueGender,   
      userType: "Patient"
    }
    if(this.validateForm.valid){
      this.authservice.registration(body).subscribe(data => { console.log(data) 
        this.toastr.success("You have successfully registered!!!");
        this.router.navigate(['login']);
      })
    }
  }

  disabledDate = (current: Date): boolean => {
    return differenceInCalendarDays(current, this.today) > 0;
  };

  updateConfirmValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.checkPassword.updateValueAndValidity());
  }

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm.controls.password.value) {
      return { confirm: true, error: true };
    }
    return {};
  };

  constructor(private fb: FormBuilder,private authservice: AuthService, private toastr: ToastrService, private router: Router) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: [null, [Validators.required]],
      surname: [null, [Validators.required]],
      email: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required, this.confirmationValidator]],
      street : [null, [Validators.required]],
      town : [null, [Validators.required]],
      state : [null, [Validators.required]],
      phoneNumberPrefix: ['+381'],
      phone: [null, [Validators.required]],
      dateOfBirth: [null, [Validators.required]]
    });
  }
}
