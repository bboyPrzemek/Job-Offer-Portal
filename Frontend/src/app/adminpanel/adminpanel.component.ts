import { Component, OnInit } from '@angular/core';
import { RouterModule, Router, ActivatedRoute, NavigationEnd, NavigationStart, Scroll } from '@angular/router';
import { CommonModule } from '@angular/common';
import { LoginService } from '../login.service';
import { NavigationComponent } from "../navigation/navigation.component";

@Component({
  selector: 'app-adminpanel',
  standalone: true,
  imports: [RouterModule, CommonModule, NavigationComponent],
  templateUrl: './adminpanel.component.html',
  styleUrl: './adminpanel.component.css'
})

export class AdminpanelComponent implements OnInit {

  constructor(private loginService : LoginService, private router: Router) {}

  currentRoute: String = '';

  ngOnInit(): void {
    this.router.events.subscribe((event: any) => {
      if (event instanceof Scroll) {
        this.currentRoute = (event.routerEvent.url.substring(event.routerEvent.url.lastIndexOf('/') + 1));
      }
    });
  }

  logout(){
    this.loginService.logout().subscribe(response=>{
      if (response.status == 200){
        this.router.navigate(["/login"]);
      }
    });
  }
}
