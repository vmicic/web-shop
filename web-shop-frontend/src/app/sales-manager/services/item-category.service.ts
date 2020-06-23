import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItemCategoryService {

  constructor(
    private http: HttpClient
  ) { }

  getAllCategories() : Observable<HttpResponse<any>> {
    return this.http.get("server/item-category", {observe: 'response'});
  }

  create(category) : Observable<HttpResponse<any>> {
    return this.http.post<any>("server/item-category", category, {observe: 'response'});
  }

  update(id, category) : Observable<HttpResponse<any>> {
    return this.http.put<any>("server/item-category/" + id, category, {observe: 'response'});
  }
}
