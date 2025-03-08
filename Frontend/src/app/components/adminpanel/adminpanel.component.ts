import { Component, inject, OnInit } from '@angular/core';
import { RouterModule, Router, Scroll } from '@angular/router';
import { CommonModule } from '@angular/common';
import { LoginService } from '../../services/login.service';
import { NavigationComponent } from '../navigation/navigation.component';

@Component({
  selector: 'app-adminpanel',
  standalone: true,
  imports: [RouterModule, CommonModule, NavigationComponent],
  templateUrl: './adminpanel.component.html',
  styleUrl: './adminpanel.component.css',
})
export class AdminpanelComponent implements OnInit {
  private loginService: LoginService = inject(LoginService);
  private router: Router = inject(Router);

  public currentRoute: String = '';

  public ngOnInit(): void {
    this.router.events.subscribe((event: any) => {
      if (event instanceof Scroll) {
        this.currentRoute = event.routerEvent.url.substring(
          event.routerEvent.url.lastIndexOf('/') + 1
        );
      }
    });
  }

  public logout(): void {
    this.loginService.logout().subscribe((response) => {
      const statusOk: number = 200;
      if (response.status === statusOk) {
        this.router.navigate(['/login']);
      }
    });
  }
}
