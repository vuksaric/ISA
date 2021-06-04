import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const medicineQuantity = environment.medicineQuantity_url;

@Injectable({
  providedIn: 'root'
})
export class MedicineQuantityService {

  constructor(private http: HttpClient) { }

  public getMedicineDifference(id): Observable<any> {
    return this.http.get(medicineQuantity + `/medicineDifference` + `/${id}`);
  }
  
}
