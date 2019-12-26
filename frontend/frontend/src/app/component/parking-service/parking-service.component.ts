import { Component, OnInit } from '@angular/core';
import { ParkingServiceService } from 'src/app/service/parking-service.service';

@Component({
  selector: 'app-parking-service',
  templateUrl: './parking-service.component.html',
  styleUrls: ['./parking-service.component.css']
})
export class ParkingServiceComponent implements OnInit {

  constructor(private  parkingService : ParkingServiceService) { }

  ngOnInit() {
  }

  public getNumberOfFreeSpots(){
    this.parkingService.freeSpots().subscribe(res => console.log(res));
  }

  public getAllParkingTickets(){
    console.log(this.parkingService.findAll().subscribe(res => console.log(res)));
  }

  public getNewParkingTicket(){
    console.log(this.parkingService.getNewParkingTicket().subscribe(res => console.log(res)));
  }

}
