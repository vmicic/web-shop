import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/app/domain/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private http: HttpClient
  ) { }

  login(user: User) : Observable<HttpResponse<any>> {
    return this.http.post<User>("server/auth/login", user, {observe: 'response'});
  }

  logout(username: String) : Observable<HttpResponse<any>> {
    return this.http.delete<any>("server/auth/logout/" + username, {observe: 'response'});
  }
}
