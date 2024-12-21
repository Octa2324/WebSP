import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from '../Services/auth-service.service';

interface Account {
  email: string;
  firstName: string;
  lastName: string;
  password: string;
  roles?: string[];
}

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent {
  account: Account = {
    email: '',
    firstName: '',
    lastName: '',
    password: '',
    roles: ['basicuser']
  };
  
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private authService: AuthServiceService,
    private router: Router
  ) {}

  createAccount(): void {
    if (!this.account.email || !this.account.password || !this.account.firstName || !this.account.lastName) {
      this.errorMessage = 'Please fill in all fields.';
      return;
    }

    this.authService.register(this.account).subscribe({
      next: () => {
        this.successMessage = 'Account created successfully!';
        this.loginAfterRegistration();
      },
      error: (error) => {
        if (error.status === 400) {
          this.errorMessage = 'Invalid input data. Please check your information.';
        } else if (error.status === 409) {
          this.errorMessage = 'Email already exists.';
        } else {
          this.errorMessage = 'Registration failed. Please try again.';
        }
        console.error('Registration error:', error);
      }
    });
  }

  private loginAfterRegistration(): void {
    const loginData = {
      email: this.account.email,
      password: this.account.password
    };

    this.authService.login(loginData).subscribe({
      next: (response) => {
        localStorage.setItem('token', response);
        localStorage.setItem('email', this.account.email);
        this.router.navigate(['/account-page']);
      },
      error: (error) => {
        console.error('Auto-login error:', error);
        // If auto-login fails, redirect to login page
        this.router.navigate(['/login-page']);
      }
    });
  }
}