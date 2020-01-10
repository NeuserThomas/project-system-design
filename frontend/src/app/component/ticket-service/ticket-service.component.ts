import { Component, OnInit } from '@angular/core';
import { TicketService } from 'src/app/service/ticket-service/ticket-service.service';
import { Screening } from 'src/app/model/ticket-service/screening';
import { Ticket } from 'src/app/model/ticket-service/ticket';

@Component({
  selector: 'app-ticket-service',
  templateUrl: './ticket-service.component.html',
  styleUrls: ['./ticket-service.component.css']
})
export class TicketServiceComponent implements OnInit {

  screenings: Screening[];
  isHidden: boolean;
  ticket : Ticket;

  chosenName: String;
  chosenHallNumber: number;
  chosenStartTime: String;
  chosenScreening: number;
  isBuying: boolean;

  constructor(private ticketService: TicketService) {
    this.isHidden = true;
    this.isBuying = false;
  }


  ngOnInit() {
  }

  public compare(a: Screening, b: Screening) {
    const screeningNameA = a.movieName;
    const screeningNameB = b.movieName;

    const screeningTimeA = a.startHour;
    const screeningTimeB = b.startHour;

    let comparison = 0;
    if (screeningNameA > screeningNameB) {
      comparison = 1;
    } else if (screeningNameA < screeningNameB) {
      comparison = -1;
    } else if (screeningNameB == screeningNameA) {
      if (screeningTimeA > screeningTimeB) {
        comparison = 1;
      } else if (screeningTimeA < screeningTimeB) {
        comparison = -1;
      }
    }
    return comparison;
  }

  public buyTicket(screening: Screening) {
    this.chosenName = screening.movieName;
    this.chosenHallNumber = screening.hallNumber;
    this.chosenStartTime = screening.startHour;
    this.chosenScreening = screening.id;
  }

  public buyAndPayTicket(){
    this.isBuying = true;
    this.ticketService.buyTicket(this.chosenScreening).subscribe(data => {
      this.ticket = data;
      this.getScreenings();
      this.isBuying = false;
      alert("Ticket bought! \nTicket: " + this.ticket.id + " , price: " + this.ticket.price);
    }
    );
  }


  public getScreenings() {
    this.isHidden = false;
    let date = new Date("2020-01-11");
    let d = date.toISOString().split('T')[0];
    this.ticketService.findAllScreeningsByDate(d).subscribe(data => {
      this.screenings = data;
      this.screenings.forEach(screening => {
        screening.startHour = screening.startTime.toString().split('T')[1];
        screening.stopHour = screening.stopTime.toString().split('T')[1];
        screening.day = screening.startTime.toString().split('T')[0];
      });
      this.screenings = this.screenings.sort(this.compare);
    });
  }

}
