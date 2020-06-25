import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserCategoryService {

  constructor(
    private http: HttpClient
  ) { }

  getAllCategories() : Observable<HttpResponse<any>> {
    return this.http.get("server/customer-category", {observe: 'response'});
  }
}
