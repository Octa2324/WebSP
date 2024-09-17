import { Component, OnInit } from '@angular/core';
import { Account } from '../Classes/Account';
import { AccountService } from '../Services/account.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-first-page',
  templateUrl: './first-page.component.html',
  styleUrl: './first-page.component.css'
})
export class FirstPageComponent implements OnInit{
  accounts: Account[] = [];
  
  constructor(private accountService: AccountService){}

  ngOnInit(): void {
    this.getAccounts();
  }

  public getAccounts(): void{
    this.accountService.getAccounts().subscribe(
      (response: Account[]) =>{
        this.accounts = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
}
