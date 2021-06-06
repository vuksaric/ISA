import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";
import { User } from '../models/user';
import jwt_decode from 'jwt-decode';

import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

const auth_url = environment.auth_url;

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private router: Router,private toastr: ToastrService) { }


  public login(body) : Observable<User>{ 
    return this.http.post<User>(auth_url + `/login`, body);
  }

  public registration(body) : Observable<User>{ 
    return this.http.post<User>(auth_url + `/registration`, body);
  }

  
  public getDataFromToken() : any
  {
    let token : any;
    let decoded_token : any;
    let result : any;
    token = localStorage.getItem("token");
    decoded_token = this.getDecodedAccessToken(token);
    result = {
      email : decoded_token.email,
      id : decoded_token.user_id, 
      type : decoded_token.user_type,
      pharmacyId: decoded_token.pharmacyId
    }
    return result
  }

  checkAuthPatient() : any{
    let type : any;
    let token : any;
    let decoded_token : any;
    token = localStorage.getItem("token");
    decoded_token = this.getDecodedAccessToken(token);
    if(token!=null){
      if(decoded_token.user_type != "Patient")
      {
        this.toastr.warning("Restricted access");
        if(decoded_token.user_type === "SystemAdministrator")
            this.router.navigate(['sysadminhome']);
        else if(decoded_token.user_type === "Dermatologist")
            this.router.navigate(['homePageDermatologist']);
        else if(decoded_token.user_type === "PharmacyAdministrator")
            this.router.navigate(['pharmacyAdmin']);
        else if(decoded_token.user_type === "Pharmacist")
            this.router.navigate(['homePagePharmacist']);
        else 
            this.router.navigate(['sysadminhome']);//supplier
      }
    }
    else{
      this.toastr.warning("You are not logged in");
      this.router.navigate(['login']);
    }

  }

  checkAuthPharmacist() : any
  {
    let token : any;
    let decoded_token : any;
    let result : any;
    token = localStorage.getItem("token");
    if(token == null)
    {
      this.router.navigate(['login']);
      this.toastr.error("You need to log in");
    }
    else
    {
      decoded_token = this.getDecodedAccessToken(token);
      if(decoded_token.user_type != "Pharmacist")
      {
        this.toastr.error("Restricted access");
        if(decoded_token.user_type === "SystemAdministrator")
            this.router.navigate(['sysadminhome']);
        else if(decoded_token.user_type === "Dermatologist")
            this.router.navigate(['homePageDermatologist']);
        else if(decoded_token.user_type === "PharmacyAdministrator")
            this.router.navigate(['pharmacyAdmin']);

        else if(decoded_token.user_type === "Patient")
            this.router.navigate(['homepage']);
        else 
            this.router.navigate(['sysadminhome']);//supplier
      }

      result = {
          id : decoded_token.user_id, 
          type : decoded_token.user_type
      }
    }

    return result;
  }

  checkAuthDermatologist() : any
  {
    let token : any;
    let decoded_token : any;
    let result : any;
    token = localStorage.getItem("token");
    if(token == null)
    {
      this.router.navigate(['login']);
      this.toastr.error("You need to log in");
    }
      
    else
    {
      decoded_token = this.getDecodedAccessToken(token);
      if(decoded_token.user_type != "Dermatologist")
      {
        this.toastr.error("Restricted access");
        if(decoded_token.user_type === "SystemAdministrator")
            this.router.navigate(['sysadminhome']);
        else if(decoded_token.user_type === "Pharmacist")
            this.router.navigate(['homePagePharmacist']);
        else if(decoded_token.user_type === "PharmacyAdministrator")
            this.router.navigate(['pharmacyAdmin']);
        else if(decoded_token.user_type === "Patient")
            this.router.navigate(['homepage']);
        else 
            this.router.navigate(['sysadminhome']);//supplier
      }

      result = {
          id : decoded_token.user_id, 
          type : decoded_token.user_type
      }
    }

    return result;
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    }
    catch (Error) {
      return null;
    }
  }
}


