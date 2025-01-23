import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from '../Services/account.service';
import { AuthServiceService } from '../Services/auth-service.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
  accountDetails: any;
  isFriend: boolean = false;
  isOwnProfile: boolean = false;

  email1: string = ''; 
  email2: string = '';

  constructor(
    private route: ActivatedRoute,
    private accountService: AccountService,
    private authService: AuthServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (!this.authService.isLoggedIn()) {
      console.log('User not logged in. Redirecting to login page.');
      this.router.navigate(['/login-page']);
      return;
    }

    const email = this.route.snapshot.paramMap.get('email');
    if (email) {
      this.getAccountDetails(email);
    } else {
      console.error('Invalid Email');
    }

    const sessionEmail = sessionStorage.getItem('email');
    if (sessionEmail) {
      console.log('Email of the session: ', sessionEmail);
      this.email1 = sessionEmail;
    } else {
      console.log('No email found, logging out');
      this.authService.logout();
    }
    
  }

  getAccountDetails(email: string): void {
    this.accountService.getAccountByEmail(email).subscribe({
      next: (data) => {
        this.accountDetails = data;
        this.email2 = this.accountDetails.email;

        this.isOwnProfile = this.email1 === this.email2;

        if (!this.isOwnProfile) {
          this.checkIfFriends();
        }
      },
      error: (error) => {
        console.error('Error fetching account details:', error);
      }
    });
  }

  checkIfFriends(): void {
    this.accountService.getAccountFriends(this.email1).subscribe({
      next: (friends) => {
        this.isFriend = friends.some((friend) => friend.email === this.accountDetails.email);
      },
      error: (error) => {
        console.error('Error checking friendship:', error);
      }
    });
  }

  addFriend() {
    this.accountService.addFriend(this.email1 , this.email2).subscribe(
      (response) => {
        //alert('Friend added successfully');
        console.log(response);
        this.isFriend = true;
      },
      (error) => {
        //alert('Error adding friend');
        console.error(error);
      }
    );
  }

  deleteFriend() {
    this.accountService.removeFriend(this.email1, this.email2).subscribe({
      next: () => {
        console.log('Friend removed successfully');
        this.isFriend = false;
      },
      error: (error) => {
        console.error('Error removing friend:', error);
      }
    });
  }

}
