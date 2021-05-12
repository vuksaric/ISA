import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";
import { User } from '../models/user';

const auth_url = environment.auth_url;

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(body) : Observable<User>{ 
    return this.http.post<User>(auth_url + `/login`, body);
  }

  public registration(body) : Observable<User>{ 
    return this.http.post<User>(auth_url + `/registration`, body);
  }
}
