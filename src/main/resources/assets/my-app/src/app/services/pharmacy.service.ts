import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";
import { Pharmacy } from '../models/pharmacy';
import { NzTableFilterFn, NzTableFilterList, NzTableSortFn, NzTableSortOrder } from 'ng-zorro-antd/table';

const pharmacy_url = environment.pharmacy_url;

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {


  constructor(private http: HttpClient) { }

  public getPharmacies(): Observable<Pharmacy[]> {
    return this.http.get<Pharmacy[]>(pharmacy_url+'/all');
  }

  public registerPharmacy(body): Observable<any> {
    return this.http.post(pharmacy_url+'/register',body);
  }

  public getPharmaciesByMedicineQuantity(id): Observable<Pharmacy[]> {
    return this.http.get<Pharmacy[]>(pharmacy_url+`/all/${id}`);
  }

  public subscribedPharmacies(id): Observable<Pharmacy[]> {
    return this.http.get<Pharmacy[]>(pharmacy_url+`/subscribed/${id}`);
  }

  public getPharmaciesDermatologist(id): Observable<Pharmacy[]> {
    return this.http.get<Pharmacy[]>(pharmacy_url+`/allDermatologist/${id}`);
  }

  public getPharmaciesWithFreeAppointment(date): Observable<Pharmacy[]>{
    return this.http.get<Pharmacy[]>(pharmacy_url+`/getPharmaciesWithFreeAppointment/${date}`);
  }

  public getAvailablePharmacistsByPharmacy(id, date):any{
    return this.http.get(pharmacy_url+`/getAvailablePharmacistsByPharmacy/${id}/${date}`);
  }

  public getDermatologistsFromPharmacy(id): Observable<any> {
    return this.http.get<any>(pharmacy_url+`/allDermatologistPharmacy/${id}`);
  }

  public getPharmacistsFromPharmacy(id): Observable<any> {
    return this.http.get<any>(pharmacy_url+`/allPharmacistPharmacy/${id}`);
  }

  public getMedicineFromPharmacy(id): Observable<any>{
    return this.http.get<any>(pharmacy_url+`/allMedicinesPharmacy/${id}`);
  }

  public getPharmacyMark(id): Observable<any>{
    return this.http.get<any>(pharmacy_url+`/getPharmacyMark/${id}`);
  }

  public getPharmacy(id): Observable<any>{
    return this.http.get<any>(pharmacy_url+`/getPharmacy/${id}`);
  }  

  public addMedicineQuantity(medicineId, pharmacyId,body): Observable<any>{
    return this.http.put<any>(pharmacy_url+`/newMedicineQuantity/${medicineId}/${pharmacyId}`, body);
  }

  public search(input,id): Observable<any>{
    return this.http.get<any>(pharmacy_url+`/searchMedicineQuantity/${input}/${id}`);
  }

  public removeMedicineQuantity(medicineId, pharmacyId): Observable<any>{
    return this.http.put<any>(pharmacy_url+`/removehMedicineQuantity/${medicineId}/${pharmacyId}`, null);
  }

  public getMedicineInPharmacy(id): Observable<any>{
    return this.http.get<any>(pharmacy_url+`/allMedicinesInPharmacy/${id}`);
  }

  public searchPharmacistInPharmacy(input,id): Observable<any>{
    return this.http.get<any>(pharmacy_url+`/pharmacistInPharmacy/${input}/${id}`);
  }

  public addPharmacistInPharmacy(pharmacyId,body): Observable<any>{
    return this.http.put<any>(pharmacy_url+`/addPharmacistInPharmacy/${pharmacyId}`, body);
  }

  public removePharmacistInPharmacy(pharmacistId, pharmacyId): Observable<any>{
    return this.http.put<any>(pharmacy_url+`/removePharmacistInPharmacy/${pharmacistId}/${pharmacyId}`, null);
  }

  public searchDermatologistInPharmacy(input,id): Observable<any>{
    return this.http.get<any>(pharmacy_url+`/dermatologistInPharmacy/${input}/${id}`);
  }

  public addDermatologistInPharmacy(pharmacyId,dermatologistId, body): Observable<any>{
    return this.http.put<any>(pharmacy_url+`/addDermatologistInPharmacy/${pharmacyId}/${dermatologistId}`,body );
  }

  public removeDermatologistInPharmacy(dermatologistId, pharmacyId): Observable<any>{
    return this.http.put<any>(pharmacy_url+`/removeDermatologistInPharmacy/${dermatologistId}/${pharmacyId}`, null);
  }

  public getDifferenceDermatologist(id): Observable<any>{
    return this.http.get<any>(pharmacy_url+`/getDifferenceDermatologist/${id}`);
  }

  public getWorkingHoursOfDermatologist(body) : Observable<any>{
    return this.http.post<any>(pharmacy_url+`/getWorkingHours`, body);
  }

  public makeReservation(body) : Observable<any>{
    return this.http.post<any>(pharmacy_url+`/makeReservation`, body);
  }


}
