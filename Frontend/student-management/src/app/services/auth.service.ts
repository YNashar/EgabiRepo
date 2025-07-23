import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/v1/auth/login';
  private roleKey = 'userRole';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { username, password }).pipe(
      tap(res => {
        localStorage.setItem(this.roleKey, res.role);
      })
    );
  }

  getRole(): string | null {
    return localStorage.getItem(this.roleKey);
  }

  logout() {
    localStorage.removeItem(this.roleKey);
  }
} 