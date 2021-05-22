import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";
import { Pharmacy } from '../models/pharmacy';
import { NzTableFilterFn, NzTableFilterList, NzTableSortFn, NzTableSortOrder } from 'ng-zorro-antd/table';

const pharmacy_url = environment.pharmacy_url;

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {


  constructor(private http: HttpClient) { }

  public getPharmacies(): Observable<Pharmacy[]> {
    return this.http.get<Pharmacy[]>(pharmacy_url+'/all');
  }
}
