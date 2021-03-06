import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/model/hall-planning-service/movie';
import { MovieService } from 'src/app/service/hall-planning-service/movie-service.service';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  movies: Movie[];

  selectedMovie: Movie;

  hide = false;

  changeHide(val: boolean) {
    this.hide = val;
    if(val===false){
      this.selectedMovie=null;
    }
  }

  constructor(private movieService: MovieService) { }

  ngOnInit() {
    this.movieService.findAll().subscribe(data => {
      this.movies = data;
    })
  }

  onSelect(movie:Movie): void{
    this.selectedMovie=movie;
    this.changeHide(true);
  }
}
