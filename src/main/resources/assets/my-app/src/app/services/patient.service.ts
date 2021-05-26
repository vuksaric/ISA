import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";
import { Profile } from '../models/profile';

const patient_url = environment.patient_url;
const user_url = environment.userProfile_url;

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) { }

  public getProfile(id): Observable<Profile> {
    return this.http.get<Profile>(patient_url + `/${id}`);
  }
  
  public editProfile(body): Observable<Profile>{
    return this.http.post<Profile>(user_url, body);
  }
}
