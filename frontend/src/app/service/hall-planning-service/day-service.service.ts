import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Day } from '../../model/hall-planning-service/day';
import { Observable } from 'rxjs/Observable';
import { Cinema } from '../../model/hall-planning-service/cinema';
import { environment } from 'src/environments/environment';


@Injectable()
export class DayService {
  private dayUrl: string;

  URL = `http://${process.env.HALL_PLANNING_SERVICE_HOST}:${process.env.HALL_PLANNING_SERVICE_PORT}/`;


  constructor(private http: HttpClient) {
    this.dayUrl = this.URL + 'planning';
  }

  public findAll(): Observable<Day[]> {
    return this.http.get<Day[]>(this.dayUrl);
  }

  public findDaysForCinema(cinema: Cinema) {
    return this.http.get<Day[]>(this.dayUrl + "/cinema/" + cinema.id);
  }

  public save(day: Day) {
    return this.http.post<Day>(this.dayUrl, day);
  }
}
