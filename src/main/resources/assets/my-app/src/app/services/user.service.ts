import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";
import { User } from '../models/user';

const user_url = environment.userProfile_url;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public editProfile(body) : Observable<any>{ 
    return this.http.put(user_url + `/edit`, body);
  }

  public changePassword(body) : Observable<any>{ 
    return this.http.put(user_url + `/changePassword`, body);
  } 
}
