import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

const complaint_url = environment.complaint_url;

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  constructor(private http: HttpClient) { }

  public newComplaint(body){
    return this.http.post(complaint_url,body);
  }
  public getAll(): any{
    return this.http.get(complaint_url+"/all");
  }
  public editComplaint(body){
    return this.http.put(complaint_url+'/edit',body);
  }
}
