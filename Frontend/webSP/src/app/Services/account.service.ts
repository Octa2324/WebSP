import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from '../Classes/Account';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private accountUrl = 'http://localhost:8080/api/accounts/';

  constructor(private http: HttpClient) { }

  public getAccounts(): Observable<Account[]>{
    return this.http.get<Account[]>(`${this.accountUrl}`);
  }

  public createAccount(account: Account): Observable<Account>{
    return this.http.post<Account>(`${this.accountUrl}`,account);
  }
}
