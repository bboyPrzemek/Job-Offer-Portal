import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) {}
  private url = 'http://localhost:8080/';

  login(params : String){
     const headers= new HttpHeaders({
          'Content-Type': 'application/x-www-form-urlencoded'
      })
    return this.http.post<any>(this.url + "login", params, {headers: headers, withCredentials : true, observe : "response"})
  }

  logout(){
    return this.http.get<any>(this.url + "logout", {withCredentials : true, observe : "response"})
  }
}


