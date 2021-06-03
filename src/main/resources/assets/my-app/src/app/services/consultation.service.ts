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

  public getMedicines(id): Observable<any>{
    return this.http.get(consultation_url + `/getMedicines/${id}`);
  }

  public prescribeMedicine(body) : Observable<any>{
    return this.http.put(consultation_url + `/prescribe`,body);
  }

  public getReplacements(body): Observable<any>{
    return this.http.post(consultation_url + `/replacements`,body);
  } 

  public finish(body): Observable<any>{
    return this.http.post(consultation_url + `/finish`,body);
  }

  public getFreePeriods(body) : Observable<any>{
    return this.http.post(consultation_url + `/freePeriods`,body);
  }

  public getFutureByPatient(id): Observable<any>{
    return this.http.get(consultation_url + `/getFutureByPatient/${id}`);
  }

  public newPharmacist(body): Observable<any>{
    return this.http.post(consultation_url + `/newPharmacist`,body);
  }

  public addPoint(body) : Observable<any>{
    return this.http.put(consultation_url + `/addPoint`,body);
  }

}
