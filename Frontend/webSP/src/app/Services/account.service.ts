import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Account, AccountDTO } from '../Classes/Account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getAllAccounts(): Observable<AccountDTO[]> {
    return this.http.get<AccountDTO[]>(`${this.apiUrl}/accounts`);
  }

  getAccountById(id: number): Observable<AccountDTO> {
    return this.http.get<AccountDTO>(`${this.apiUrl}/account/${id}`);
  }

  getAccountByEmail(email: string): Observable<AccountDTO> {
    return this.http.get<AccountDTO>(`${this.apiUrl}/account/email/${email}`);
  }

  searchAccounts(name: string): Observable<AccountDTO[]> {
    return this.http.get<AccountDTO[]>(`${this.apiUrl}/account/search?name=${name}`);
  }

  updateAccount(id: number, account: Account): Observable<AccountDTO> {
    return this.http.put<AccountDTO>(`${this.apiUrl}/account/${id}`, account);
  }

  deleteAccount(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/account/${id}`);
  }
}