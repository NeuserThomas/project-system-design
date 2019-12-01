import { Component, OnInit } from '@angular/core';
import { Cinema } from "../../model/cinema"
import { CinemaService } from "../../service/cinema-service.service"


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
