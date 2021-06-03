import { HttpClient } from '@angular/common/http';
import { ThrowStmt } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const supplier_url = environment.supplier_url;

@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  constructor(private http: HttpClient) { }

  public getProfileInfo(email) : Observable<any>{
    return this.http.get(supplier_url + '/getByEmail/${email}');
  }
}
