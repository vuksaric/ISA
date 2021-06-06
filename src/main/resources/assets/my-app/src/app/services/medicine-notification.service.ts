import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";

const medicineNotification_url = environment.medicineNotification_url;

@Injectable({
  providedIn: 'root'
})
export class MedicineNotificationService {

  constructor(private http: HttpClient) { }

  public getMedicineNotifications(id): Observable<any> {
    return this.http.get(medicineNotification_url+'/getAll' + `/${id}`);
  }
}
