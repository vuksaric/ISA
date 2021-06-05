import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  validateForm2 : FormGroup;

  constructor( private fb: FormBuilder, private toastr: ToastrService, private userService : UserService) { }
  
  ngOnInit(): void {

    this.validateForm2 = this.fb.group({
      oldPassword:[null,[Validators.required]],
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required, this.confirmationValidator]]
    })
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

  submitForm2(){
    if(this.validateForm2.valid){
      const body = {
        oldPassword: this.validateForm2.controls['oldPassword'].value,
        password: this.validateForm2.controls['password'].value,
        checkPassword: this.validateForm2.controls['checkPassword'].value,
      }
      this.userService.changePassword(body).subscribe(result => {
        this.toastr.success("Successfully changed");
      })
    }
  }
  
}
