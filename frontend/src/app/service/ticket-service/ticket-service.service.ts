import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Screening } from '../../model/ticket-service/screening';
import { Ticket } from '../../model/ticket-service/ticket';

@Injectable({
  providedIn: 'root'
})

export class TicketService {

  private ticketUrl: string;

  constructor(private http: HttpClient) { 
    this.ticketUrl = "http://" + window.location.hostname + ':' + location.port + "/ticket";
  }

  public findAllScreeningsByDate(d:String): Observable<Screening[]> {
    return this.http.get<Screening[]>(this.ticketUrl+'/screenings/'+d);
  }

  public buyTicket(id: number): Observable<Ticket> {
    return this.http.get<Ticket>(this.ticketUrl+'/buyTicket/?screeningId=' + id);
  }

}
