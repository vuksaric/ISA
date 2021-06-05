import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const offer_url = environment.offer_url;

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  constructor(private http: HttpClient) { }

  public getAllBySupplier(email): Observable<any>{
    return this.http.get(offer_url + `/getAllBySupplier/${email}`);
  }
}
