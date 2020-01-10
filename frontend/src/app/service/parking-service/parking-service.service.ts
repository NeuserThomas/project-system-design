import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { ParkingTicket } from '../../model/parking-service/parking-ticket';

@Injectable({
  providedIn: 'root'
})
export class ParkingService {
  private parkingUrl: string;

  constructor(private http: HttpClient) { 
    this.parkingUrl = "http://localhost:2301/parking";
  }

  public findAll(): Observable<ParkingTicket[]> {
    return this.http.get<ParkingTicket[]>(this.parkingUrl+'/tickets');
  }

  public getNewParkingTicket(): Observable<ParkingTicket> {
    return this.http.get<ParkingTicket>(this.parkingUrl+'/getTicket');
  }

  public freeSpots(){
    return this.http.get(this.parkingUrl+'/numberOfFreeSpots');
  }

}
