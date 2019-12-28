import { Component, OnInit, Input } from '@angular/core';
import { ShopItem } from 'src/app/model/shop-service/shop-item';
import { StockService } from 'src/app/service/shop-service/stock-service.service';

@Component({
  selector: 'app-shop-item-list',
  templateUrl: './shop-item-list.component.html',
  styleUrls: ['./shop-item-list.component.css']
})
export class ShopItemListComponent implements OnInit {

  @Input() items:ShopItem[];
  name: any;
  price: number;
  description: String;

  constructor(private stockService:StockService) {
    
   }

  ngOnInit() {
    this.stockService.findProducts().subscribe(data=>{
      this.items=data;
    });
  }

  add(){
    var shopItem = new ShopItem();
    shopItem.name=this.name;
    shopItem.price=this.price;
    shopItem.description=this.description;
    this.stockService.postShopItem(shopItem).subscribe(
      data=>{
        this.stockService.findProducts().subscribe(data=>{
          this.items=data;
        });
      }
    )
  }
}
