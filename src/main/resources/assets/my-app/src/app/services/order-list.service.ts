import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const orderList_url = environment.orderList_url;

@Injectable({
  providedIn: 'root'
})
export class OrderListService {

  constructor(private http: HttpClient) { }

  public getAll(): Observable<any>{
    return this.http.get(orderList_url + `/getAll`);
  }
}
