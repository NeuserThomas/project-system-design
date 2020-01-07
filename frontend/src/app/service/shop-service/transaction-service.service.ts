import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Transaction } from 'src/app/model/shop-service/transaction';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  SHOP_URL = window.location.hostname + "/transaction";

  constructor(private http: HttpClient) {
    console.log(this.SHOP_URL);
  }

  public tryAndSell(transaction: Transaction): Observable<Object> {
    return this.http.post<Object>(this.SHOP_URL, transaction);
  }
}
