import { Component, OnInit } from '@angular/core';
import { Transaction } from 'src/app/model/shop-service/transaction';
import { TransactionService } from 'src/app/service/shop-service/transaction-service.service';
import { Stock } from 'src/app/model/shop-service/stock';
import { StockService } from 'src/app/service/shop-service/stock-service.service';
import { ShopItem } from 'src/app/model/shop-service/shop-item';

@Component({
  selector: 'app-new-transaction',
  templateUrl: './new-transaction.component.html',
  styleUrls: ['./new-transaction.component.css']
})
export class NewTransactionComponent implements OnInit {

  transaction:Transaction;
  showAlert:boolean=false;
  message:String;
  good:boolean=true;
  stockId:number;
  names:String[];
  prodId:number;
  amount:number;
  loading:boolean=false;
  constructor(private transactionService:TransactionService, private stockService:StockService) { }

  ngOnInit() {
    this.stockService.getStockNames().subscribe(data=>{
      this.names=data;
    })
  }

  selectName(name:String){
    this.stockService.findStockByName(name).subscribe(data=>{
      this.stockId=data.id;
    })
  }

  newTransaction(){
    this.showAlert=false;
    this.transaction=null;
    this.transaction=new Transaction();
  }

  cancel(){
    this.showAlert=false;
    this.transaction=null;
  }

  save(){
    if(this.transaction==null){
      this.showAlertMethod(false,"Please start a transaction!");
    } else {
      if(this.transaction.soldItems==null || !this.transaction.soldItems){
        if(this.transaction.soldItems==null){
          console.log(1);
        }
        if(! this.transaction.soldItems){
          console.log(2);
        }
        console.log("transaction");
        this.showAlertMethod(false,"Please add items");
      }
      this.transaction.stockId=this.stockId;
      this.loading=true;
      this.transactionService.tryAndSell(this.transaction).subscribe(
        data=>{
          this.showAlertMethod(true,"Saved!");
          this.loading=false;
        },
        error=>{
          console.log(error);
          this.showAlertMethod(false,"Something went wrong!");
          this.loading=false;
        }
      );
      this.transaction=null;

    }
    
  }

  showAlertMethod(good:boolean,message:String){
    this.good=good;
    this.message=message;
    this.showAlert=true;
  }

  closeAlert(){
    this.showAlert=false;
  }

  add(){
    if(this.transaction.soldItems[this.prodId]){
      this.transaction.soldItems[this.prodId]+=this.amount;
    } else {
      this.transaction.soldItems[this.prodId]=this.amount;
    }
  }

}
