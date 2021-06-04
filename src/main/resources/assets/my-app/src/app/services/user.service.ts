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

  constructor(private http : HttpClient) { }

  public getUserProfile(username) : Observable<Profile>{
    return this.http.get<Profile>(user_url+`/${username}`);
  }
}
