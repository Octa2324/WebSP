import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from './Services/auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'webSP';
  isLoggedIn = false;
  userName = 'Guest';
  userPicture = 'https://http.cat/200';

  constructor(private authService: AuthServiceService, private router: Router) {
  }

  ngOnInit(): void {
    // Subscribe to token changes
    this.authService.tokenSubject.subscribe(token => {
      this.isLoggedIn = !!token;
      this.userName = token ? sessionStorage.getItem('email') || 'Guest' : 'Guest';
      this.userName = this.userName.slice(0,this.userName.indexOf("@"));
    });
  }

  navigateToAccount(): void {
    console.log('Attempting to navigate to account page');
    
    if (this.isLoggedIn) {
      this.router.navigate(['/account-page'], { 
        skipLocationChange: false,
        replaceUrl: false 
      }).then(
        success => console.log('Navigation success', success),
        error => console.error('Navigation error', error)
      );
    } else {
      console.log('Not logged in');
    }
  }

  logout(): void {
    this.authService.logout();
  }
}