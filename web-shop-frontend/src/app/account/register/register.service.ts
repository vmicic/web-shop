import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { User } from 'src/app/domain/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(
    private http: HttpClient
  ) { }

  register(user: User) : Observable<HttpResponse<any>> {
    return this.http.post('server/auth/register', user, {observe : 'response'});
  }
}
