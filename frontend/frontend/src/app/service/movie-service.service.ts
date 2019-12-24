import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Movie } from '../model/movie';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class MovieService {

  private movieUrl: string;

  constructor(private http: HttpClient) {
    this.movieUrl = 'http://localhost:2223/movie';
  }

  public findAll(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.movieUrl);
  }

  public save(movie: Movie) {
    return this.http.post<Movie>(this.movieUrl, movie);
  }
}