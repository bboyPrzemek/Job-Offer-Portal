import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'navigation',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css',
})
export class NavigationComponent implements OnInit {
  private userService: UserService = inject(UserService);

  public username: string = '';
  public photo: string = '/assets/images/person.jpeg';
  public authenticated: boolean = false;

  public ngOnInit(): void {
    this.userService.getUserInfo().subscribe((result) => {
      this.authenticated = true;
      this.username = result.displayName;
    });
  }
}
