import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class RegistrationService {

  constructor(private http: HttpClient) {}
  private url = 'http://localhost:8080/register';

  register(params: any) {
    return this.http.post < any > (this.url, params, {
      responseType: 'text' as 'json'
    })
  }
}
