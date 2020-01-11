import { Component, OnInit } from '@angular/core';
import { ParkingService } from 'src/app/service/parking-service/parking-service.service';

@Component({
  selector: 'app-parking-service',
  templateUrl: './parking-service.component.html',
  styleUrls: ['./parking-service.component.css']
})
export class ParkingServiceComponent implements OnInit {

  numberOfFreeSpots: String;
  showNumber: boolean;
  newTicketInfo: String;
  validatedTicketInfo: String;
  showTicketInfo: boolean;
  showValidatedTicketInfo: boolean;
  showValidateFields: boolean;
  pTicketId: number;
  cTicketId: number;
  exitParkingId: number;
  showExitParkingFields: boolean;
  exitParkingInfo: String;
  showExitParkingInfo: boolean;

  constructor(private parkingService: ParkingService) {
    this.showNumber = false;
    this.showTicketInfo = false;
    this.showValidateFields = false;
    this.showValidatedTicketInfo = false;
    this.showExitParkingFields = false;
    this.showExitParkingInfo = false;
    this.pTicketId = 0;
    this.cTicketId=0;
    this.exitParkingId = 0;
  }

  ngOnInit() {
  }

  public getNumberOfFreeSpots() {
    this.parkingService.freeSpots().subscribe(res => {
      console.log(res);
      this.showNumber = true;
      this.numberOfFreeSpots = res.toString();
    });
  }

  public getNewParkingTicket() {
    this.parkingService.getNewParkingTicket().subscribe(res => {
      console.log(res);
      this.showTicketInfo = true;
      this.newTicketInfo = "ID : " + res.id + " enterTime: " + res.enterTime.toString().split("T")[1] + " -- validated: " + res.validated;
    }
    );
  }

  public validateParkingTicketInit() {
    this.showValidateFields = true;
  }

  public validateParkingTicket() {
    this.parkingService.validateParkingTicket(this.pTicketId, this.cTicketId).subscribe(res => 
      {
        console.log(res);
        this.showValidatedTicketInfo = true;
        this.validatedTicketInfo = "ID : " + res.id + " enterTime: " + res.enterTime.toString().split("T")[1] + " -- validated: " + res.validated + " validationTime: " + res.validationTime.toString().split("T")[1];
      }
      );
  }

  public exitParkingInit(){
    this.showExitParkingFields = true;
  }

  public exitParking() {
    this.parkingService.exitParking(this.exitParkingId).subscribe(res => {
      console.log(res);
      this.showExitParkingInfo=true;
      this.exitParkingInfo = "Exited parking with parkingticket: " + this.exitParkingId + ", see you next time!";
    }
    );
  }

}
