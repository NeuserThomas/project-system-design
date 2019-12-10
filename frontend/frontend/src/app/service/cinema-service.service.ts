import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cinema } from '../model/cinema';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class CinemaService {
  private cinemaUrl: string;

  constructor(private http: HttpClient) {
    this.cinemaUrl = 'http://localhost:2223/cinema';
  }

  public findAll(): Observable<Cinema[]> {
    return this.http.get<Cinema[]>(this.cinemaUrl);
  }

  public save(cinema: Cinema) {
    return this.http.post<Cinema>(this.cinemaUrl, cinema);
  }
}
