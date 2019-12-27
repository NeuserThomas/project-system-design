import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Day } from '../model/day';
import { Observable } from 'rxjs/Observable';
import { Cinema } from '../model/cinema';


@Injectable()
export class DayService {
  private dayUrl: string;

  constructor(private http: HttpClient) {
    this.dayUrl = 'http://localhost:2223/planning';
  }

  public findAll(): Observable<Day[]> {
    return this.http.get<Day[]>(this.dayUrl);
  }

  public findDaysForCinema(cinema:Cinema){
    return this.http.get<Day[]>(this.dayUrl+"/cinema/"+cinema.id);
  }

  public save(day: Day) {
    return this.http.post<Day>(this.dayUrl, day);
  }
}
