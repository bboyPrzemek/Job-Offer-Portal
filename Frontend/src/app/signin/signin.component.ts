import { Component } from '@angular/core';
import { FormsModule} from '@angular/forms';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';
import { NavigationComponent } from "../navigation/navigation.component";

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [FormsModule, NavigationComponent],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css'
})

export class SigninComponent {

  username = '';
  password = ''

  constructor(private loginService : LoginService, private router:Router){}

  onSubmit(){
    let params = new URLSearchParams();
    params.set('username', this.username);
    params.set('password', this.password);

    this.loginService.login(params.toString()).subscribe(response=>{
      if (response.status == 200){
        this.loginService.getUserInfo().subscribe();
        this.router.navigate(["/"]);
      }
    });
  }
}
