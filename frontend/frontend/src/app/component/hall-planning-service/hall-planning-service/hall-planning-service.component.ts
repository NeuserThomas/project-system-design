import { Component, OnInit } from '@angular/core';
import {CommonModule} from '@angular/common'
@Component({
  selector: 'app-hall-planning-service',
  templateUrl: './hall-planning-service.component.html',
  styleUrls: ['./hall-planning-service.component.css']
})
export class HallPlanningServiceComponent implements OnInit {

  selectedComponent: String;

  constructor() { }

  ngOnInit() {
  }

  onSelect(selectedComponent:String){
    this.selectedComponent=selectedComponent;
    console.log(this.selectedComponent);
  }
}
