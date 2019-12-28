import { Component, OnInit } from '@angular/core';
import { Day } from "../../../model/hall-planning-service/day"
import { DayService } from "../../../service/hall-planning-service/day-service.service"

@Component({
  selector: 'app-day-list',
  templateUrl: './day-list.component.html',
  styleUrls: ['./day-list.component.css']
})
export class DayListComponent implements OnInit {

  days: Day[];

  constructor(private dayService: DayService) { }

  ngOnInit() {
    this.dayService.findAll().subscribe(data => {
      console.log(data);
      this.days = data;
    })
  }

}
