import { Component, inject } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NavigationComponent } from '../navigation/navigation.component';
import { RegistrationService } from './services/registration.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule, NavigationComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  private registrationService: RegistrationService = inject(RegistrationService);
  public displayName: string = '';
  public email: string = '';
  public password: string = '';
  public confirmPassword: string = '';

  public onSubmit(form: NgForm): void {
    this.registrationService.register(form.value).subscribe({
      next: (value: string) => {
        if (value === 'Created') {
          alert('created');
        }
      },
      error: (e: any) => alert(e.error),
    });
  }
}
