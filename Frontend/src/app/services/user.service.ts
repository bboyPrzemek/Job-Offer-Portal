import { Observable } from 'rxjs';
import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private http: HttpClient = inject(HttpClient);
  private url = 'http://localhost:8080/';

  public getUserInfo(): Observable<any> {
    return this.http.get<any>(this.url + 'me', {
      withCredentials: true,
    });
  }

  public getAuthenticationStatus(): Observable<any> {
    return this.http.get<any>(this.url + 'status', {
      withCredentials: true,
      observe: 'response',
      responseType: 'text' as 'json',
    });
  }
}
