import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PromotionService {

  constructor(
    private http: HttpClient
  ) { }

  getAllPromotions(): Observable<HttpResponse<any>> {
    return this.http.get("server/promotions", { observe: 'response' });
  }

  createPromotion(promotion): Observable<HttpResponse<any>> {
    return this.http.post<any>("server/promotions", promotion, { observe: 'response' });
  }

  updatePromotion(id, promotion): Observable<HttpResponse<any>> {
    return this.http.put<any>("server/promotions/" + id, promotion, { observe: 'response' });
  }
}
