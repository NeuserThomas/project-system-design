import { Component, OnInit } from '@angular/core';
import { Cinema } from "../../../model/hall-planning-service/cinema"
import { CinemaService } from "../../../service/hall-planning-service/cinema-service.service"


@Component({
  selector: 'app-cinema-list',
  templateUrl: './cinema-list.component.html',
  styleUrls: ['./cinema-list.component.css']
})
export class CinemaListComponent implements OnInit {

  cinemas: Cinema[];

  constructor(private cinemaService: CinemaService) { }

  ngOnInit() {
    this.cinemaService.findAll().subscribe(data => {
      this.cinemas = data;
    })
  }

}
