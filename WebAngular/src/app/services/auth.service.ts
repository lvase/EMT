import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {catchError, map, Observable, tap, throwError} from "rxjs";
import {jwtDecode} from "jwt-decode";
import {StorageService} from "./storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:9090/api/user';

  constructor(private http: HttpClient, private router: Router, private storageService: StorageService) {
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, {username, password}, {withCredentials: true}).pipe(
      tap(token => {
        this.storageService.setItem('token', token);
      })
    )
  }

  logout(): any {
    this.storageService.removeItem('token');
    this.router.navigate(['/login']);
  }

  getUserRole(): string | null {
    const token = this.storageService.getItem('token');
    if (token) {
      const decodedToken = jwtDecode<any>(token);
      return decodedToken.role;
    }
    return null;
  }

  isAdmin() {
    return this.getUserRole() === 'ROLE_ADMIN';
  }

  isUserOrLibrarian() {
    const role = this.getUserRole();
    return role === 'ROLE_USER' || role === 'ROLE_LIBRARIAN';
  }

  isLoggedIn() {
    return !!this.storageService.getItem('token');
  }
}
