import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-account-settings',
  standalone: true,
  imports: [],
  templateUrl: './account-settings.component.html',
  styleUrl: './account-settings.component.css'
})
export class AccountSettingsComponent implements OnInit {

  constructor(private loginService: LoginService) {}

  userInfo: any = {};

  ngOnInit() {
    this.loginService.getUserInfo().subscribe(result => {
      this.userInfo = result;
    })
  }
}
