import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError, map,tap } from 'rxjs/operators';
import { LoginService } from '../services/login.service';
import { ActivatedRouteSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class AuthGuard implements CanActivate {

  constructor(private router: Router, private loginService: LoginService) {}

  canActivate(route: ActivatedRouteSnapshot): Observable < boolean > {
    return this.loginService.getUserInfo().pipe(
      map((isLoggedIn) => {
        if (route.data["requiresAuth"] == false) {
          return true;
        }
        if (!isLoggedIn) {
          this.loginService.logout().subscribe();
          this.router.navigate(['/login']);
          return false;
        }
        return true;
      }),
      catchError(() => {
        this.loginService.logout().subscribe();
        if (route.data["requiresAuth"] == false) {
          return of(true);
        }
        this.router.navigate(['/login']);
        return of(false);
      })
    );
  }
}
