import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {AuthService} from '../auth/auth.service';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(public auth: AuthService,
    private route : Router) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const re = /confirm-account/gi||/register/;
    const regRegister = /register/;


    if(request.url.search(re) !== - 1 || request.url.search(regRegister) !== -1) {
      return next.handle(request);
    }

    if (this.auth.isAuthenticated()) {
        //console.log('Token is existing');
        request = request.clone({
        setHeaders: {
            Authorization: `Bearer ${this.auth.getToken()}`
        }
        });
    } else {
      this.route.navigate(['/login']);
    }
    return next.handle(request);
  }
}
