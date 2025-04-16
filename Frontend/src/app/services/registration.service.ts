import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class RegistrationService {
  private url = 'http://localhost:8080/register';

  constructor(private http: HttpClient) {}

  register(params: any) {
    return this.http.post<any>(this.url, params, {
      responseType: 'text' as 'json'
    })
  }
}
