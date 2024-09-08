import { AfterViewInit, Component } from '@angular/core';

declare const gapi: any;

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements AfterViewInit {

  ngAfterViewInit() {
    this.googleInit();
  }

  googleInit() {
    gapi.load('auth2', () => {
      const auth2 = gapi.auth2.init({
        client_id: 'YOUR_CLIENT_ID.apps.googleusercontent.com',
        cookiepolicy: 'single_host_origin',
        scope: 'profile email'
      });
      this.attachSignin(document.querySelector('.g-signin2'), auth2);
    });
  }

  attachSignin(element: any, auth2: any) {
    auth2.attachClickHandler(element, {},
      (googleUser: any) => {
        const profile = googleUser.getBasicProfile();
        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());
      }, (error: any) => {
        console.log(JSON.stringify(error, undefined, 2));
      });
  }

}
