import { UserService } from './../../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd/modal';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { differenceInCalendarDays } from 'date-fns';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.css']
})
export class AdminProfileComponent implements OnInit {

  isVisible = false;
  isVisible2 = false;
  validateForm!: FormGroup;
  validateForm2 : FormGroup;
  today = new Date();

  constructor(private modal: NzModalService, private fb: FormBuilder, private toastr: ToastrService, private userService : UserService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: [null, [Validators.required, this.validateName]],
      surname: [null, [Validators.required, this.validateSurname]],
      phone: [null, [Validators.required, this.validatePhone]],
      street: [null, [Validators.required]],
      town: [null, [Validators.required]],
      state: [null, [Validators.required]]
    });

    this.validateForm2 = this.fb.group({
      oldPassword:[null,[Validators.required]],
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required, this.confirmationValidator]]
    })
  }

  validateName = (group: FormControl): { [s: string]: boolean } => {
    let phone = group.value;
    let regex = /^[a-zA-Z ]+$/;
    if (!regex.test(phone)) {
      return { name: true }
    }
  }

  validateSurname = (group: FormControl): { [s: string]: boolean } => {
    let phone = group.value;
    let regex = /^[a-zA-Z ]+$/;
    if (!regex.test(phone)) {
      return { surname: true }
    }
  }

  validatePhone = (group: FormControl): { [s: string]: boolean } => {
    let phone = group.value;
    let regex = /^[0-9]*$/;
    if (!regex.test(phone)) {
      return { numbers: true }
    }
  }

  showModal(): void {
    this.isVisible = true;
  }

  showModal2(): void {
    this.isVisible2 = true;
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  handleCancel2(): void {
    this.isVisible2 = false;
  }

  submitForm(){
    if (this.validateForm.valid) {
      const body = {
        name: this.validateForm.controls['name'].value,
        surname: this.validateForm.controls['surname'].value,
        address: this.validateForm.controls['street'].value,
        town: this.validateForm.controls['town'].value,
        state: this.validateForm.controls['state'].value,
        phone: this.validateForm.controls['phone'].value
      }
      this.userService.editProfile(body).subscribe(result => {
        this.handleCancel();
        this.toastr.success("Successfully edited");
      })
    }
  }

  submitForm2(){
    if(this.validateForm2.valid){
      const body = {
        oldPassword: this.validateForm2.controls['oldPassword'].value,
        password: this.validateForm2.controls['password'].value,
        checkPassword: this.validateForm2.controls['checkPassword'].value,
      }
      this.userService.changePassword(body).subscribe(result => {
        this.handleCancel();
        this.toastr.success("Successfully changed");
      })
    }
  }

  getAdminInfo(){

  }

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm2.controls.password.value) {
      return { confirm: true, error: true };
    }
    return {};
  };

  updateConfirmValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm2.controls.checkPassword.updateValueAndValidity());
  }

  updateNameValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.name.updateValueAndValidity());
  }

  updateSurnameValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.surname.updateValueAndValidity());
  }

  updatePhoneValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.phone.updateValueAndValidity());
  }

}
