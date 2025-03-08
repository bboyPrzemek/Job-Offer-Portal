import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class JobOfferService {
  private url = 'http://localhost:8080/';
  private http: HttpClient = inject(HttpClient);

  getJobOffers() {
    return this.http.get<any>(this.url + 'userOffers', {
      withCredentials: true,
    });
  }

  searchOffers(queryParams?: string): Observable<any> {
    if (queryParams === undefined) {
      return this.http.get<any>(this.url, { withCredentials: true });
    } else {
      return this.http.get<any>(this.url + '?' + queryParams, {
        withCredentials: true,
      });
    }
  }

  createJobOffer(body: any): Observable<any> {
    return this.http.post<any>(this.url + 'create', body, {
      withCredentials: true,
      responseType: 'text' as 'json',
      observe: 'response',
    });
  }

  getOfferById(Id: any): Observable<any> {
    return this.http.get<any>(this.url + 'offer/' + Id, {
      withCredentials: true,
    });
  }
}
