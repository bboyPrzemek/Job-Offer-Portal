import { HttpClient } from  '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JobOfferService {

  private url = 'http://localhost:8080/';

  constructor(private http: HttpClient) {}

  getJobOffers() {
    return this.http.get < any > (this.url + "userOffers", {
      withCredentials: true
    });
  }

  searchOffers(queryParams ? : String) {
    if (queryParams === undefined) {
      return this.http.get < any > (this.url, {
        withCredentials: true
      });
    } else {
      return this.http.get < any > (this.url + "?" + queryParams, {
        withCredentials: true
      });
    }
  }

  createJobOffer(body: any) {
    return this.http.post < any > (this.url + "create", body, {
      withCredentials: true,
      responseType: 'text' as 'json',
      observe: 'response'
    });
  }

  getOfferById(Id: any) {
    return this.http.get < any > (this.url + "offer/" + Id, {
      withCredentials: true
    });
  }
}
