import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { LoginService } from './login.service';
import { User } from 'src/app/domain/user';
import { Router } from '@angular/router';
import {JwtHelperService} from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(false);

  constructor(
    private http: HttpClient,
    private loginService: LoginService,
    private router: Router,
    private jwtHelper: JwtHelperService
  ) { }

  login() {
      this.loggedIn.next(true);
  }

  isTokenPresent() {
    if (localStorage.getItem('token')) {
      return true;
    } else {
      return false;
    }
  }

  getToken() {
    return localStorage.getItem('token');
  }

  logout() {
    //this.loginService.logout(localStorage.getItem('username')).subscribe(response => {
    //localStorage.clear();
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
    //})
  }

  isAuthenticated() {
    if (this.isTokenPresent()) {
      const token = localStorage.getItem('token');
      return !this.jwtHelper.isTokenExpired(token);
   }
   return false;
  }

  getUserRole() {
    const token = localStorage.getItem('token');
    const payload = this.jwtHelper.decodeToken(token);
    return payload.role;
  }

  get isLoggedIn() {
    if(this.isAuthenticated()){
      this.loggedIn.next(true);
      return this.loggedIn;
    } else {
      this.loggedIn.next(false);
      return this.loggedIn;
    }
  }
}
