import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const workdayDermatologist_url = environment.workdayDermatologist_url;

@Injectable({
  providedIn: 'root'
})
export class WorkdayDermatologistService {

  constructor(private http: HttpClient) { }

  public getExaminations(id): Observable<any> {
    return this.http.get(workdayDermatologist_url + `/getExaminations/${id}`);
    }
}
