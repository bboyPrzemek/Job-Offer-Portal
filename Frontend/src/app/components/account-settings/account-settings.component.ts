import { Component, inject, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-account-settings',
  standalone: true,
  imports: [],
  templateUrl: './account-settings.component.html',
  styleUrl: './account-settings.component.css',
})
export class AccountSettingsComponent implements OnInit {
  private userService: UserService = inject(UserService);

  public userInfo: any = {};

  public ngOnInit(): void {
    this.userService.getUserInfo().subscribe((result: any) => {
      this.userInfo = result;
    });
  }
}
