import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";

const pricelist_url = environment.pricelist_url;

@Injectable({
  providedIn: 'root'
})
export class PricelistService {

  constructor(private http: HttpClient) { }

  public getPricelist(id): Observable<any> {
    return this.http.get(pricelist_url+'/all' + `/${id}`);
  }

  public editMedicine(body): Observable<any> {
    return this.http.post(pricelist_url+'/edit', body);
  }

}
