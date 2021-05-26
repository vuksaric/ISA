import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";
import { VacationRequest } from '../models/VacationRequest';

const vacationRequest_url = environment.vacationRequest_url;

@Injectable({
  providedIn: 'root'
})
export class VacationRequestService {

  constructor(private http: HttpClient) { }

  public sendVacationRequest(body): Observable<any> {
    return this.http.post(vacationRequest_url + `/vacationRequestPharmacist`, body);
 }
}
