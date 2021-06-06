import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";

const bill_url = environment.bill_url;

@Injectable({
  providedIn: 'root'
})
export class BillService {

  constructor(private http: HttpClient) { }

  public getBillReport(id): Observable<any> {
    return this.http.get<any>(bill_url+'/billReport'+`/${id}`);
  }

  public getIncomeReport(id): Observable<any> {
    return this.http.get<any>(bill_url+'/incomeReport'+`/${id}`);
  }
}
