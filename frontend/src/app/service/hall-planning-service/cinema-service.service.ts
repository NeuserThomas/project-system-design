import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cinema } from '../../model/hall-planning-service/cinema';
import { Observable } from 'rxjs/Observable';
import { environment } from 'src/environments/environment';


@Injectable()
export class CinemaService {
  private cinemaUrl: string;

  URL = "nada";

  constructor(private http: HttpClient) {
    this.cinemaUrl = this.URL + 'cinema';
  }

  public findAll(): Observable<Cinema[]> {
    return this.http.get<Cinema[]>(this.cinemaUrl);
  }

  public findAllNames(): Observable<String[]> {
    return this.http.get<String[]>(this.cinemaUrl + "/getAllByName");
  }

  public findOneByName(name: String): Observable<Cinema> {
    return this.http.get<Cinema>(this.cinemaUrl + "/getOneByName/" + name);
  }

  public save(cinema: Cinema) {
    return this.http.post<Cinema>(this.cinemaUrl, cinema);
  }
}
