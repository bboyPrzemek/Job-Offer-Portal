import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private http: HttpClient = inject(HttpClient);
  private url = 'http://localhost:8080/';

  login(params: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
    });
    return this.http.post<any>(this.url + 'login', params, {
      headers: headers,
      withCredentials: true,
      observe: 'response',
    });
  }

  logout(): Observable<any> {
    return this.http.get<any>(this.url + 'logout', {
      withCredentials: true,
      observe: 'response',
    });
  }
}
