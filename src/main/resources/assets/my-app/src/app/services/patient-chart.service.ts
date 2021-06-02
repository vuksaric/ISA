import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";
import { Allergy } from '../models/allergy';
import { Examination } from '../models/examination';
import { Medicine } from '../models/medicine';

const patientChart_url = environment.patientChart_url;

@Injectable({
  providedIn: 'root'
})
export class PatientChartService {

  constructor(private http: HttpClient) { }

  public getPatientAllergy(id): Observable<Allergy[]> {
    return this.http.get<Allergy[]>(patientChart_url + `/allergies/${id}`);
  }

  public addPatientAllergy(body, id): Observable<Allergy[]> {
    return this.http.post<Allergy[]>(patientChart_url + `/addAllergy/${id}`, body);
  }

  public getFutureReservations(id) : any{
    return this.http.get(patientChart_url + `/futureReservations/${id}`);
  }

  public removeReservation(id, serialNumber): any{
   return this.http.put(patientChart_url+`/${id}/remove/${serialNumber}`, null);
  }

  public getPatientDoctors(id) : any{
    return this.http.get(patientChart_url + `/doctors/${id}`);
  }

  public getPatientPharmacist(id) : any{
    return this.http.get(patientChart_url + `/pharmacist/${id}`);
  }
  public getPatientMedicine(id) : any{
    return this.http.get(patientChart_url + `/medicine/${id}`);
  }

  public getPatientPharmacy(id) : any{
    return this.http.get(patientChart_url + `/pharmacy/${id}`);
  }
 
  public getPreviousExaminationsByPatient(id): Observable<Examination[]> {
    return this.http.get<Examination[]>(patientChart_url+'/previousExaminations'+`/${id}`);
  }

  public getPreviousConsultationsByPatient(id): any {
    return this.http.get(patientChart_url+'/previousConsultations'+`/${id}`);
  }
  public getPatientERecipes(id): any {
    return this.http.get(patientChart_url+'/eRecipe'+`/${id}`);
  }

  public getPatientERecipeMedicines(id): Observable<Medicine[]> {
    return this.http.get<Medicine[]>(patientChart_url+'/eRecipeMedicines'+`/${id}`);
  }

}
