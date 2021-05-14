import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";
import { Profile } from '../models/profile';

const pharmacist_url = environment.pharmacist_url;

@Injectable({
    providedIn: 'root'
  })
  export class PharmacistService {

    constructor(private http: HttpClient) { }
  
    public getProfile(id): Observable<Profile> {
    return this.http.get<Profile>(pharmacist_url + `/getProfile/${id}`);
    }
  }