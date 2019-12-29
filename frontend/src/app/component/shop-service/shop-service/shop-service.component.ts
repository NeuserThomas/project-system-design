import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-shop-service',
  templateUrl: './shop-service.component.html',
  styleUrls: ['./shop-service.component.css']
})
export class ShopServiceComponent implements OnInit {

  selectedComponent:String;

  constructor() { }

  ngOnInit() {
  }

  onSelect(comp:String){
    this.selectedComponent=comp;
  }

}
