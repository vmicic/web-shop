import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(
    private http: HttpClient
  ) { }

  sendCart(cart) : Observable<HttpResponse<any>> {
    return this.http.post<any>('server/order', cart, {observe: 'response'});
  }
}
