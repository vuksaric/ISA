import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Address } from '../models/address';

const address_url = environment.address_url;

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http: HttpClient) { }

  public add(address) : Observable<Address>{
    return this.http.post<Address>(address_url+'/add',address);
  }
}
