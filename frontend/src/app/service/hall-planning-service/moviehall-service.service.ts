import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Moviehall } from '../../model/hall-planning-service/moviehall';
import { Observable } from 'rxjs/Observable';
import { environment } from 'src/environments/environment';


@Injectable()
export class MoviehallService {
  private moviehallUrl: string;

  constructor(private http: HttpClient) {
    this.moviehallUrl = environment.hallPlanningServiceURL+':2223/hall';
  }

  public findAll(): Observable<Moviehall[]> {
    return this.http.get<Moviehall[]>(this.moviehallUrl);
  }

  public save(moviehall: Moviehall) {
    return this.http.post<Moviehall>(this.moviehallUrl, moviehall);
  }
}
