import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Moviehall } from '../../model/hall-planning-service/moviehall';
import { Observable } from 'rxjs/Observable';
import { environment } from 'src/environments/environment';


@Injectable()
export class MoviehallService {
  private moviehallUrl: string;
  URL = `http://${process.env.HALL_PLANNING_SERVICE_HOST}:${process.env.HALL_PLANNING_SERVICE_PORT}/`;


  constructor(private http: HttpClient) {
    this.moviehallUrl = this.URL + 'hall';
  }

  public findAll(): Observable<Moviehall[]> {
    return this.http.get<Moviehall[]>(this.moviehallUrl);
  }

  public save(moviehall: Moviehall) {
    return this.http.post<Moviehall>(this.moviehallUrl, moviehall);
  }
}
