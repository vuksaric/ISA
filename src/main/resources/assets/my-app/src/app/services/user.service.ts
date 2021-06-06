import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Profile } from '../models/profile';

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

  public getUserProfile(username) : Observable<Profile>{
    return this.http.get<Profile>(user_url+`/${username}`);
  }
  
  public getProfile(id):Observable<any>{
    return this.http.get(user_url + `/getProfile` + `/${id}`);
  }
}
