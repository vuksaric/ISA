import { Allergy } from '../models/allergy';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Medicine } from '../models/medicine';

const medicine_url = environment.medicine_url;

@Injectable({
  providedIn: 'root'
})
export class MedicineService {

  constructor(private http: HttpClient) { }

  public getDistinctMedicine(): Observable<Allergy[]> {
    return this.http.get<Allergy[]>(medicine_url + `/distinct`);
  }

  public getAllByName(name): Observable<any> {
    return this.http.get(medicine_url + `/getAllByName/${name}`);
  }

  public getAll(): Observable<any> {
    return this.http.get(medicine_url + `/getAll`);
  }

  public getById(id : number): Observable<any> {
    return this.http.get(medicine_url + `/${id}`);
  }


  public getMedicines(id): Observable<Allergy[]> {
    return this.http.get<Allergy[]>(medicine_url + `/find/${id}`);
  }

  public getAllMedicine() : Observable<Medicine[]> {
    return this.http.get<Medicine[]>(medicine_url+'/getAll');
  }
  
  public addMedicine(medicine): Observable<Medicine>{
    return this.http.post<Medicine>(medicine_url + `/add`,medicine);
  }

  public getByType(type): Observable<Medicine[]>{
    return this.http.get<Medicine[]>(medicine_url + `/getByType/${type}`);
  }
}
