import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
import { NavigationComponent } from '../navigation/navigation.component';

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [FormsModule, NavigationComponent],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css',
})
export class SigninComponent {
  private loginService: LoginService = inject(LoginService);
  private router: Router = inject(Router);

  public username: string = '';
  public password: string = '';

  onSubmit() {
    let params = new URLSearchParams();
    params.set('username', this.username);
    params.set('password', this.password);

    this.loginService.login(params.toString()).subscribe((response: any) => {
      const statsuOk: number = 200;
      if (response.status === statsuOk) {
        this.router.navigate(['/']);
      }
    });
  }
}
