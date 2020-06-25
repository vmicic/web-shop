import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(
    private http: HttpClient
  ) { }

  getAllOrders() : Observable<HttpResponse<any>> {
    return this.http.get("server/order", {observe: 'response'});
  }
}
