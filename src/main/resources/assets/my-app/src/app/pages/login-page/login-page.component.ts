import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import jwt_decode from 'jwt-decode';


@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  token: any;
  decoded_token: any;
  errorLogin: boolean = false;
  validateForm!: FormGroup;
  email: string;
  password: string;

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

    this.email = this.validateForm.value.email;
    this.password = this.validateForm.value.password;

    const body = {
      email: this.email,
      password: this.password
    }

    this.authService.login(body).subscribe(data => {
      const user = data;
      localStorage.setItem('user', JSON.stringify(user));
      localStorage.setItem('token', JSON.stringify(user.token));
      console.log(this.getDecodedAccessToken(data.token));
      this.decoded_token = this.getDecodedAccessToken(data.token);
      if(this.decoded_token.user_type === "SystemAdministrator")
          this.router.navigate(['sysadminhome']);
      else if(this.decoded_token.user_type === "Pharmacist")
          this.router.navigate(['homePagePharmacist']);
      else if(this.decoded_token.user_type === "Dermatologist")
          this.router.navigate(['homePageDermatologist']);
      else if(this.decoded_token.user_type === "PharmacyAdministrator")
          this.router.navigate(['pharmacyAdmin']);
      else if(this.decoded_token.user_type === "Patient")
          this.router.navigate(['homepage']);
      else 
          this.router.navigate(['sysadminhome']);//supplier
    }, error => {
      this.errorLogin = true;
    })
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    }
    catch (Error) {
      return null;
    }
  }

  constructor(private fb: FormBuilder, private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      email: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });
  }

}
