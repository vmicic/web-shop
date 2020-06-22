import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(
    private http: HttpClient
  ) { }

  getItemsRefill() : Observable<HttpResponse<any>> {
    return this.http.get("server/items/refill", {observe: 'response'});
  }

  refillItem(id, amount) : Observable<HttpResponse<any>> {
    return this.http.put<any>("server/items/" + id, amount, {observe: 'response'});
  }
}
