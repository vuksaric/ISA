import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  validateForm2: FormGroup;
  id: number;
  dataToken;

  constructor(private fb: FormBuilder, private toastr: ToastrService, private router: Router,
    private userService: UserService, private authorizationService: AuthService) { }

  ngOnInit(): void {
    this.dataToken = this.authorizationService.getDataFromToken();
    this.id = this.dataToken.id;
    this.validateForm2 = this.fb.group({
      oldPassword: [null, [Validators.required]],
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

  submitForm2() {
    if (this.validateForm2.valid) {
      const body = {
        email: this.dataToken.email,
        oldPassword: this.validateForm2.controls['oldPassword'].value,
        newPassword: this.validateForm2.controls['password'].value,
      }
      this.userService.changePassword(body).subscribe(result => {
        this.toastr.success("Successfully changed");
        if (this.dataToken.type == "SystemAdministrator")
          this.router.navigate(['sysadminhome']);
        else if (this.dataToken.type == "Pharmacist")
          this.router.navigate(['homePagePharmacist']);
        else if (this.dataToken.type == "Dermatologist")
          this.router.navigate(['homePageDermatologist']);
        else if (this.dataToken.type == "PharmacyAdministrator")
          this.router.navigate(['pharmacyAdmin']);
        else
          this.router.navigate(['sysadminhome']);
      })
    }
  }

}
