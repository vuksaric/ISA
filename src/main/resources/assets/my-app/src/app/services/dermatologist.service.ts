import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Profile } from '../models/profile';

const dermatologist_url = environment.dermatologist_url;

@Injectable({
  providedIn: 'root'
})
export class DermatologistService {

  constructor(private http: HttpClient) { }

  public getProfile(id): Observable<Profile> {
    return this.http.get<Profile>(dermatologist_url + `/getProfile/${id}`);
  }
  
  public getWorkdays(id): Observable<any>{
    return this.http.get(dermatologist_url + `/getWorkdays/${id}`);
  }

  public getAll(): Observable<any>{
    return this.http.get(dermatologist_url + `/all`);
  }

  public search(input : string) : Observable<any>{
    return this.http.get(dermatologist_url + `/search/${input}`);
  }

  public delete(id : number) : Observable<any>{
    return this.http.put(dermatologist_url + `/delete`, id);
  }

  public addDermatologist(id : number):  Observable<any>{
    return this.http.post(dermatologist_url + `/add`, id);
  }

  public checkVacation(body): Observable<any> {
    return this.http.post(dermatologist_url + `/checkVacation`, body);
  }
}
