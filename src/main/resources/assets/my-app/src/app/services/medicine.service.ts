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
  public getMedicines(): Observable<Allergy[]> {
    return this.http.get<Allergy[]>(medicine_url + `/find`);
  }

  public getAllMedicine() : Observable<Medicine[]> {
    return this.http.get<Medicine[]>(medicine_url+'/getAll');
  }
}
