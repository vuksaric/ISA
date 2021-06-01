import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';


const review_url = environment.review_url;

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }

  public findReview(idPatient, idObject, type) : any{
    return this.http.get(review_url + `/find/${idPatient}/${idObject}/${type}`);
  }

  public saveReview(body) : any{
    return this.http.post(review_url, body);
  }

  public editReview(body) : any{
    return this.http.put(review_url, body);
  }
}
