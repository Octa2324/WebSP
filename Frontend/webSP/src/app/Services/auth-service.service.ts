import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Account } from '../Classes/Account';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private apiUrl = 'http://localhost:8080';
  public tokenSubject = new BehaviorSubject<string | null>(null); 

  constructor(private http: HttpClient, private router: Router) {
    window.addEventListener('storage', this.syncAuth.bind(this));
    const token = sessionStorage.getItem('token');
    if (token) {
      this.tokenSubject.next(token);
    }
  }
  
  private syncAuth(event: StorageEvent): void {
    if (event.key === 'token') {
      this.tokenSubject.next(event.newValue);
    }
  }
  

  private clearAuthData(): void {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('email');
    this.tokenSubject.next(null);
  }

  login(credentials: { email: string, password: string }): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, credentials, { responseType: 'text' as 'json' })
      .pipe(
        tap(token => {
          sessionStorage.setItem('token', token);
          sessionStorage.setItem('email', credentials.email);
          this.tokenSubject.next(token);
        })
      );
  }

  register(account: Account): Observable<Account> {
    return this.http.post<Account>(`${this.apiUrl}/createnewaccount`, account);
  }

  logout(): void {
    this.clearAuthData();
    this.router.navigate(['/first-page']);
  }

  getToken(): string | null {
    return this.tokenSubject.value;
  }

  isLoggedIn(): boolean {
    const token = this.getToken();
    return !!token;
  }
}