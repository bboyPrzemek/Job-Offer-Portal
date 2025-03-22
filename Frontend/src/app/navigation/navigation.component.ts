import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'navigation',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css'
})
export class NavigationComponent implements OnInit {

  constructor(private loginService : LoginService) {}

  username: string = '';
  photo: string = '/assets/images/person.jpeg';
  authenticated: boolean = false;

  ngOnInit() {
    this.loginService.user$.subscribe(user=>{
      if (user){
        this.username = user.displayName;
        this.authenticated = true;
      }
    });
  }
}
