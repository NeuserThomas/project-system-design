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

  constructor(private stockService:StockService) {
    
   }

  ngOnInit() {
    this.stockService.findProducts().subscribe(data=>{
      this.items=data;
    });
  }



}
