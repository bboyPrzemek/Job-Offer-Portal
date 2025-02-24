import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-account-settings',
  standalone: true,
  imports: [],
  templateUrl: './account-settings.component.html',
  styleUrl: './account-settings.component.css'
})
export class AccountSettingsComponent implements OnInit{

  constructor(private userService :  UserService){}

  userInfo : any = {};

  ngOnInit() {
      this.userService.getUserInfo().subscribe(result =>{
          this.userInfo = result;
      })
  }

}
