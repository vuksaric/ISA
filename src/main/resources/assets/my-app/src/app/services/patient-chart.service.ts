import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";
import { Allergy } from '../models/allergy';

const patientChart_url = environment.patientChart_url;

@Injectable({
  providedIn: 'root'
})
export class PatientChartService {

  constructor(private http: HttpClient) { }

  public getPatientAllergy(id): Observable<Allergy[]> {
    return this.http.get<Allergy[]>(patientChart_url + `/allergies/${id}`);
  }

  public addPatientAllergy(body, id): Observable<Allergy[]> {
    return this.http.post<Allergy[]>(patientChart_url + `/addAllergy/${id}`, body);
  }

  public getFutureReservations(id) : any{
    return this.http.get(patientChart_url + `/futureReservations/${id}`);
  }

  public removeReservation(id, serialNumber): any{
   return this.http.put(patientChart_url+`/${id}/remove/${serialNumber}`, null);
  }
}
