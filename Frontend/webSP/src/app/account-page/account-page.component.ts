import { Component, OnInit } from '@angular/core';
import { AccountService } from '../Services/account.service';
import { Router } from '@angular/router';
import { AuthServiceService } from '../Services/auth-service.service';

@Component({
  selector: 'app-account-page',
  templateUrl: './account-page.component.html',
  styleUrls: ['./account-page.component.css']
})
export class AccountPageComponent implements OnInit {
  accountDetails: any;
  accounts: any;

  accountFriends: any;

  constructor(
    private accountService: AccountService,
    private router: Router,
    private authService: AuthServiceService
  ) {
    console.log('AccountPageComponent - Constructor called');
  }

  ngOnInit(): void {
    console.log('AccountPageComponent - ngOnInit started');
    console.log('Is Logged In:', this.authService.isLoggedIn());
    console.log('Stored Email:', sessionStorage.getItem('email'));

    if (!this.authService.isLoggedIn()) {
      console.log('Not logged in, redirecting to login page');
      this.router.navigate(['/login-page']);
      return;
    }

    const email = sessionStorage.getItem('email');
    if (email) {
      console.log('Fetching account details for:', email);
      this.getAccountDetails(email);
      this.getAllAccounts();
      this.getAccountFriends(email);
    } else {
      console.log('No email found, logging out');
      this.authService.logout();
    }
  }

  getAccountDetails(email: string): void {
    this.accountService.getAccountByEmail(email).subscribe({
      next: (data) => {
        console.log('Account details fetched:', data);
        this.accountDetails = data;
      },
      error: (error) => {
        console.error('Error fetching account:', error);
        this.authService.logout();
      }
    });
  }

  getAllAccounts(): void {
    this.accountService.getAllAccounts().subscribe({
      next: (data) => {
        console.log('Accounts displayed now: ', data);
        this.accounts = data;
      },
      error: (error) => {
        console.error('Error fetching accounts: ', error);
      }
    });
  }

  getAccountFriends(email: string): void {
    this.accountService.getAccountFriends(email).subscribe({
      next: (data) =>{
        console.log('Account friends displayed now: ', data);
        this.accountFriends = data;
      },
      error: (error) => {
        console.error('Error getting account friends: ', error);
      }
    })
  }

  logout(): void {
    this.authService.logout();
  }
}