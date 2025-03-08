import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RegistrationService {
  private http: HttpClient = inject(HttpClient);
  private url = 'http://localhost:8080/register';

  public register(params: any): Observable<any> {
    return this.http.post<any>(this.url, params, {
      responseType: 'text' as 'json',
    });
  }
}
