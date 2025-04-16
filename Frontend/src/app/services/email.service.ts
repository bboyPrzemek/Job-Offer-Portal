import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmailService {
  private url = 'http://localhost:8080/';

  constructor(private http: HttpClient) {}

  sendEmail(formData: FormData) {
    return this.http.post<any>(this.url + "apply/1", formData, {
      withCredentials: true,
      observe: "response"
    });
  }
}
