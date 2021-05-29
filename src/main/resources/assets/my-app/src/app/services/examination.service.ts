import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";
import { Examination } from '../models/examination';

const examination_url = environment.examination_url;

@Injectable({
  providedIn: 'root'
})
export class ExaminationService {

  constructor(private http: HttpClient) { }

  public getFreeExaminations(): Observable<Examination[]> {
    return this.http.get<Examination[]>(examination_url+'/getFree');
  }

  public reserveExamination(id): Observable<Examination>{
    return this.http.put<Examination>(examination_url+'/reserve'+`/${id}`, null);
  }

  public getMedicines(id): Observable<any>{
    return this.http.get(examination_url + `/getMedicines/${id}`);
  }

  public prescribeMedicine(body) : Observable<any>{
    return this.http.put(examination_url + `/prescribe`,body);
  }

  public getReplacements(body): Observable<any>{
    return this.http.post(examination_url + `/replacements`,body);
  } 

  public finish(body): Observable<any>{
    return this.http.post(examination_url + `/finish`,body);
  }

}
