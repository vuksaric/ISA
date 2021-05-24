import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";


const consultation_url = environment.consultation_url;
@Injectable({
  providedIn: 'root'
})
export class ConsultationService {

  constructor(private http: HttpClient) { }

  public getPreviousByPharmacist(id): Observable<any>{
    return this.http.get(consultation_url + `/getPreviousByPharmacist/${id}`);
  }
}
