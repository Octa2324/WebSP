import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthServiceService } from './auth-service.service';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthServiceService,
    private router: Router
  ) {}

  canActivate(): boolean {
    const isLoggedIn = this.authService.isLoggedIn();
    console.log('AuthGuard - isLoggedIn:', isLoggedIn);
  
    if (isLoggedIn) {
      console.log('AuthGuard - Access granted');
      return true;
    }
  
    console.log('AuthGuard - Access denied, redirecting to login');
    this.router.navigate(['/login-page']);
    return false;
  }
  
  
}