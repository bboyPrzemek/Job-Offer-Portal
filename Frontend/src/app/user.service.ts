import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  constructor(private http: HttpClient) {

  }

  private url = 'http://localhost:8080/';

  getUserInfo() {
    return this.http.get < any > (this.url + "me", {
      withCredentials: true
    })
  }

  getAuthenticationStatus(){
    return this.http.get < any > (this.url + "status", {
      withCredentials: true, observe : 'response', responseType  : 'text' as 'json'
    })
  }
}
