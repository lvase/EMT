import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {
  }

  login() {
    if (this.username && this.password) {
      this.authService.login(this.username, this.password).subscribe((user) => {
          if (user) {
            this.router.navigate(['/home']);
          } else {
            this.errorMessage = 'Invalid credentials';
          }
        },
        (error) => {
          this.errorMessage = 'Invalid credentials';
        }
      );
    } else {
      debugger
      this.errorMessage = 'Username and password are required';
    }
  }
}
