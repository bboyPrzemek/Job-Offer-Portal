import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UserService } from './user.service';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})

export class AuthGuard implements CanActivate{

    constructor(private userService: UserService, private router: Router) {}
    canActivate(): Observable<boolean> {
      return this.userService.getAuthenticationStatus().pipe(
        map((isLoggedIn) => {
          if (!isLoggedIn) {
            this.router.navigate(['/login']);
            return false;
          }
          return true;
        }),
        catchError(() => {
          this.router.navigate(['/login']);
          return of(false);
        })
      );
    }
  }

