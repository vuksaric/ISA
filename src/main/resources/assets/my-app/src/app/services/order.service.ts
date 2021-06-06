import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const order_url = environment.order_url;

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  public newOrder(body):Observable<any>{
    return this.http.post<any>(order_url + `/new`,body);
  }

  public deleteOrder(body):Observable<any>{
    return this.http.put<any>(order_url + `/delete`,body);
  }

  public getAll(id):Observable<any>{
    return this.http.get<any>(order_url + `/getAll/${id}`);
  }

  public acceptOffer(body):Observable<any>{
    return this.http.post<any>(order_url + `/acceptOffer`,body);
  }
}
