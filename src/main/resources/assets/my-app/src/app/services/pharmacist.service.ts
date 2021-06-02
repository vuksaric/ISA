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

  public getWorkdays(id): Observable<any> {
    return this.http.get(pharmacist_url + `/getWorkdays/${id}`);
  }

  public getReservations(id): Observable<any> {
    return this.http.get(pharmacist_url + `/getReservations/${id}`);
  }

  public newPharmacist(body): Observable<any> {
    return this.http.post(pharmacist_url + `/newPharmacist`, body);
  }

  public getAll(): Observable<any> {
    return this.http.get(pharmacist_url + `/all`);
  }

  public search(search: string): Observable<any> {
    return this.http.get(pharmacist_url + `/search/` + search);
  }

  public delete(id: number): Observable<any> {
    return this.http.put(pharmacist_url + `/delete`, id);
  }


}