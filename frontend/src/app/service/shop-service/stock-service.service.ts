import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ShopItem } from 'src/app/model/shop-service/shop-item';
import { Observable } from 'rxjs/Observable';
import { Stock } from 'src/app/model/shop-service/stock';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StockService {

  private shopUrl: string;
  private stockUrl: string;
  SHOP_URL = `http://${process.env.SHOP_SERVICE_HOST}:${process.env.SHOP_SERVICE_PORT}/`;



  constructor(private http: HttpClient) {
    this.stockUrl = this.SHOP_URL + 'stock';
    this.shopUrl = this.SHOP_URL + 'shopItem';
    console.log(this.stockUrl);
    console.log(this.shopUrl);
    console.log(this.SHOP_URL);
    console.log(process.env);
  }

  public findProducts(): Observable<ShopItem[]> {
    return this.http.get<ShopItem[]>(this.shopUrl);
  }

  public findAllStock(): Observable<Stock[]> {
    return this.http.get<Stock[]>(this.shopUrl);
  }

  public findStockByName(name: String): Observable<Stock> {
    return this.http.get<Stock>(this.stockUrl + "/getByName/" + name);
  }

  public getStockNames(): Observable<String[]> {
    return this.http.get<String[]>(this.stockUrl + "/names");
  }

  public postStock(stock: Stock): Observable<Stock> {
    return this.http.post<Stock>(this.stockUrl, stock);
  }

  public putStock(stock: Stock): Observable<any> {
    return this.http.put<Stock>(this.stockUrl, stock);
  }

  public postShopItem(shopItem: ShopItem) {
    return this.http.post<ShopItem>(this.shopUrl, shopItem);
  }

}
