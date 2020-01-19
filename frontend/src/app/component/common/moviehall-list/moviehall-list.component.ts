import { Component, OnInit } from '@angular/core';
import { Moviehall } from "../../../model/hall-planning-service/moviehall"
import { MoviehallService } from "../../../service/hall-planning-service/moviehall-service.service"
import { Movie } from 'src/app/model/hall-planning-service/movie';

@Component({
  selector: 'app-moviehall-list',
  templateUrl: './moviehall-list.component.html',
  styleUrls: ['./moviehall-list.component.css']
})
export class MoviehallListComponent implements OnInit {

  moviehalls: Moviehall[];

  constructor(private moviehallService: MoviehallService) { }

  ngOnInit() {
    this.moviehallService.findAll().subscribe(data => {
      console.log(data);
      this.moviehalls = data;
    })
  }

  onWaitListSubmit(movie:Movie){
    
  }
}
