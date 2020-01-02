import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Movie } from '../../model/hall-planning-service/movie';
import { Observable } from 'rxjs/Observable';
import { environment } from 'src/environments/environment';

@Injectable()
export class MovieService {

  private movieUrl: string;
  URL = `http://${process.env.HALL_PLANNING_SERVICE_HOST}:${process.env.HALL_PLANNING_SERVICE_PORT}/`;

  constructor(private http: HttpClient) {
    this.movieUrl = this.URL+'movie';
  }

  public findAll(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.movieUrl);
  }

  public findById(id): Observable<Movie> {
    console.log(id);
    return this.http.get<Movie>(this.movieUrl+"/"+id);
  }

  public save(movie: Movie) {
    return this.http.post<Movie>(this.movieUrl, movie);
  }

  public search(title:String): Observable<Movie>{
    return this.http.get<Movie>(this.movieUrl+"/getMovieByName/"+title);
  }
}
