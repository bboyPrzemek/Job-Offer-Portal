import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, tap, Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class LoginService {
  private url = 'http://localhost:8080/';
  private userSubject = new BehaviorSubject <any> (this.getUserFromStorage());
  private loadingSubject = new BehaviorSubject <boolean> (true);
  user$ = this.userSubject.asObservable();
  loading$ = this.loadingSubject.asObservable();

  constructor(private http: HttpClient) {}

  login(params: String) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    })
    return this.http.post<any>(this.url + "login", params, {
      headers: headers,
      withCredentials: true,
      observe: "response"
    });
  }

  getUserInfo(): Observable <any> {
    return this.http.get<any>(this.url + "me", {
      withCredentials: true
    }).pipe(
      tap((user: any) => {
        if (user) {
          console.log('u1')
          localStorage.setItem('user', JSON.stringify(user));
          this.userSubject.next(user);
        } else {
          console.log('u2');
          localStorage.removeItem('user');
          this.userSubject.next(null);
        }
      })
    )
  }

  logout() {
    localStorage.removeItem('user');
    this.userSubject.next(null);
    return this.http.get<any>(this.url + "logout", {
      withCredentials: true,
      observe: "response"
    });
  }

  private getUserFromStorage() {
    return JSON.parse(localStorage.getItem('user') || 'null');
  }
}
