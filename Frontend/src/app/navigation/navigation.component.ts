import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'navigation',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css'
})
export class NavigationComponent implements OnInit{

  constructor(private userService : UserService){}

  username : String = '';
  photo : String = '/assets/images/person.jpeg';
  authenticated : Boolean = false;

  ngOnInit(){
    this.userService.getUserInfo().subscribe(result =>{
      this.authenticated = true;
      this.username = result.displayName;
    })
  }
}
