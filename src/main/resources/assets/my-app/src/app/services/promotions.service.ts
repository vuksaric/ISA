import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";

const promotions_url = environment.promotions_url;

@Injectable({
  providedIn: 'root'
})
export class PromotionsService {

  constructor(private http: HttpClient) { }

  public newPromotion(body): Observable<any> {
    return this.http.post(promotions_url + `/new`, body);
  }

  public getAll(): Observable<any> {
    return this.http.get(promotions_url + `/all`);
  }
}
