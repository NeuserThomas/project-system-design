import { Component, OnInit, Input } from '@angular/core';
import { Movie } from 'src/app/model/hall-planning-service/movie';
import { MovieService } from 'src/app/service/hall-planning-service/movie-service.service';

@Component({
  selector: 'app-search-movie',
  templateUrl: './search-movie.component.html',
  styleUrls: ['./search-movie.component.css']
})
export class SearchMovieComponent implements OnInit {

  movie:Movie;
  error: Boolean;
  constructor(private movieService: MovieService) { }

  ngOnInit() {
  }

  search(title:String){
    this.movieService.search(title).subscribe(data => {
      this.movie = data;
      this.error=false;
    },
    error => {
      this.error=true;
      console.log(error);
    })
  }

  save(){
    this.movieService.save(this.movie).subscribe(error => {
      console.log(error);
    })
  }

}
