import { Component, OnInit } from '@angular/core';
import { StockService } from 'src/app/service/shop-service/stock-service.service';
import { Stock } from 'src/app/model/shop-service/stock';
import { ShopItem } from 'src/app/model/shop-service/shop-item';

@Component({
  selector: 'app-list-stock',
  templateUrl: './list-stock.component.html',
  styleUrls: ['./list-stock.component.css']
})
export class ListStockComponent implements OnInit {

  /**
   * I realize that the class is inificient (as it get every shop item, instead of pagination. This would be updated if it should be more than bare end)
   */

  names:String[];
  stock: Stock;
  prods:ShopItem[];
  show:boolean=false;
  good:boolean=true;
  message:String;
  prodIds: any[];
  prodId:number;
  amount:number;

  constructor(private stockService:StockService) { }

  ngOnInit() {
    this.stockService.getStockNames().subscribe(data=>{
      this.names=data;
    })
  }

  selectName(name:String){
    this.stockService.findStockByName(name).subscribe(data=>{
      this.prodIds=[];
      this.stock=data;
      console.log(this.stock);
      this.prodIds=Object.keys(this.stock.thresholdPerProduct);
      this.stockService.findProducts().subscribe(data=>{
        this.prods=data;
      });
    })
  }

  save(){
    console.log(this.stock);
    this.stockService.putStock(this.stock).subscribe(
      data=>{
        this.showAlert(true,"Sucessfully saved.");
      },
      error=>{
        console.log(error);
        this.showAlert(false,"Something went wrong!");
      }
    )
  }

  showAlert(good:boolean,message:String){
    this.show=true;
    this.good=good;
    this.message=message;
  }

  closeAlert(){
    this.show=false;
  }

  getProd(id:number):object {
    var obj = this.prods.filter(function(node) {
        return node.id==id;
    });
    return obj;
  }


}
