import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const reservation_url = environment.reservation_url;

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) { }

  public issue(body): any{
    return this.http.put(reservation_url + `/issue`, body);
  }

  public makeReservation(body) : any{
    return this.http.post(reservation_url, body);
  }
}
