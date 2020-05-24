import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserItemsService {

  constructor(
    private http: HttpClient
  ) { }

  getAll() : Observable<HttpResponse<any>> {
    return this.http.get<any>('server/items', {observe: 'response'});
  }
}
