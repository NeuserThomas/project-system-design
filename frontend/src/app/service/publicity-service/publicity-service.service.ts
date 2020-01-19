import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AdMovie } from '../../model/publicity-service/ad-movie';

@Injectable({
  providedIn: 'root'
})
export class PublicityService {
  private publicityUrl: string;

  constructor(private http: HttpClient) {
    this.publicityUrl = 'http://' + window.location.hostname + ':' + location.port + '/publicity/publicity';
  }

  public getAdMovieByCategory(category: string): Observable<AdMovie> {
    return this.http.get<AdMovie>(this.publicityUrl + '/' + category);
  }

  public getMaxDuration() {
    return this.http.get(this.publicityUrl + '/maxDuration');
  }
}
