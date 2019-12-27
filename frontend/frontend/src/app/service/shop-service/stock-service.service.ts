import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ShopItem } from 'src/app/model/shop-service/shop-item';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class StockService {

  private shopUrl: string;
  private stockUrl: string;

  constructor(private http: HttpClient) {
    this.stockUrl = 'http://localhost:2230/stock';
    this.shopUrl = 'http://localhost:2230/shopItem';
  }

  public findProducts(): Observable<ShopItem[]> {
    return this.http.get<ShopItem[]>(this.shopUrl);
  }

  public findAllStock(): Observable<ShopItem[]> {
    return this.http.get<ShopItem[]>(this.shopUrl);
  }

  public findStockByName(name:String): Observable<ShopItem[]> {
    return this.http.get<ShopItem[]>(this.stockUrl+"/getByName/"+name);
  }

  public getNames(): Observable<String[]> {
    return this.http.get<String[]>(this.stockUrl+"/names");
  }
}
