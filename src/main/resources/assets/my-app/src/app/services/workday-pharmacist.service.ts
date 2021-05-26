import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const workdayPharmacist_url = environment.workdayPharmacist_url;

@Injectable({
  providedIn: 'root'
})
export class WorkdayPharmacistService {

  constructor(private http: HttpClient) { }

  public getConsultations(id): Observable<any> {
    return this.http.get(workdayPharmacist_url + `/getConsultations/${id}`);
    }

}
