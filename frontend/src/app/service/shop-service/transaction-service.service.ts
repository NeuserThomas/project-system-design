import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Transaction } from 'src/app/model/shop-service/transaction';
import { Observable } from 'rxjs/Observable';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  
  private shopUrl: string;

  constructor(private http: HttpClient) {
    this.shopUrl = environment.shopServiceURL+'/transaction';
  }

  public tryAndSell(transaction: Transaction): Observable<Transaction> {
    return this.http.post<Transaction>(this.shopUrl,transaction);
  }
  
  


}
