import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cinema } from '../../model/hall-planning-service/cinema';
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

  public findAllNames(): Observable<String[]> {
    return this.http.get<String[]>(this.cinemaUrl+"/getAllByName");
  }

  public findOneByName(name:String): Observable<Cinema> {
    return this.http.get<Cinema>(this.cinemaUrl+"/getOneByName/"+name);
  }

  public save(cinema: Cinema) {
    return this.http.post<Cinema>(this.cinemaUrl, cinema);
  }
}
