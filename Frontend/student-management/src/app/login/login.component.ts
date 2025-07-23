import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username = '';
  password = '';
  error = '';

  constructor(private auth: AuthService, private router: Router) {}

  login() {
    this.auth.login(this.username, this.password).subscribe({
      next: res => {
        if (res.role === 'ADMIN') {
          this.router.navigate(['/admin-dashboard']);
        } else if (res.role === 'STUDENT') {
          this.router.navigate(['/student-form']);
        }
      },
      error: () => {
        this.error = 'Invalid credentials';
      }
    });
  }
} 