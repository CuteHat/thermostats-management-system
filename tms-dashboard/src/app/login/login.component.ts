import { Component } from '@angular/core';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  // @ts-ignore
  accessToken: string;

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.setAccessToken(this.accessToken);
    this.router.navigate(['/dashboard']);
  }
}
