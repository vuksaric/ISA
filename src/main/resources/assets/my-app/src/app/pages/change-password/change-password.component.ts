import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  validateForm2 : FormGroup;
  id : number;
  dataToken;

  constructor( private fb: FormBuilder, private toastr: ToastrService, private userService : UserService, private authorizationService : AuthService) { }
  
  ngOnInit(): void {
    this.dataToken = this.authorizationService.getDataFromToken();
    this.id = this.dataToken.id;
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
        user_id : this.id,
        oldPassword: this.validateForm2.controls['oldPassword'].value,
        newpassword: this.validateForm2.controls['password'].value,
      }
      this.userService.changePassword(body).subscribe(result => {
        this.toastr.success("Successfully changed");
      })
    }
  }
  
}
