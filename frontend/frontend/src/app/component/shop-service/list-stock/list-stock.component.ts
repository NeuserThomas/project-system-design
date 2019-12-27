import { Component, OnInit } from '@angular/core';
import { StockService } from 'src/app/service/shop-service/stock-service.service';

@Component({
  selector: 'app-list-stock',
  templateUrl: './list-stock.component.html',
  styleUrls: ['./list-stock.component.css']
})
export class ListStockComponent implements OnInit {

  names:String[];
  stock: Object;
  constructor(private stockService:StockService) { }

  ngOnInit() {
    this.stockService.getNames().subscribe(data=>{
      this.names=data;
    })
  }

  selectName(name:String){
    this.stockService.findStockByName(name).subscribe(data=>{
      this.stock=data;
    })
  }


}
