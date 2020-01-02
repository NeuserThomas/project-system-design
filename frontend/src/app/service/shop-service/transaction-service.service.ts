import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Transaction } from 'src/app/model/shop-service/transaction';
import { Observable } from 'rxjs/Observable';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  SHOP_URL = `http://${process.env.SHOP_SERVICE_HOST}:${process.env.SHOP_SERVICE_PORT}/transaction`;

  constructor(private http: HttpClient) {
  }

  public tryAndSell(transaction: Transaction): Observable<Transaction> {
    return this.http.post<Transaction>(this.SHOP_URL, transaction);
  }
}
