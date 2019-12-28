import { Component, OnInit, Output } from '@angular/core';
import { Cinema } from 'src/app/model/hall-planning-service/cinema';
import { CinemaService } from 'src/app/service/hall-planning-service/cinema-service.service';
import { CalendarEvent } from 'angular-calendar';
import { DayService } from 'src/app/service/hall-planning-service/day-service.service';
import { Day } from 'src/app/model/hall-planning-service/day';
import { MovieService } from 'src/app/service/hall-planning-service/movie-service.service';
import { Moviehall } from 'src/app/model/hall-planning-service/moviehall';
import { Movie } from 'src/app/model/hall-planning-service/movie';

@Component({
  selector: 'app-show-planning',
  templateUrl: './show-planning.component.html',
  styleUrls: ['./show-planning.component.css']
})
export class ShowPlanningComponent implements OnInit {

  cinemaNames: String[];
  cinema:Cinema;
  hall: Moviehall;
  days: Day[];
  @Output() events: CalendarEvent[] = [];
  movies = new Map<String, Movie>();
  constructor(private cinemaService: CinemaService,private dayService: DayService,private movieService: MovieService) { }

  ngOnInit() {
    this.cinemaService.findAllNames().subscribe(data => {
      this.cinemaNames = data;
      console.log(this.cinemaNames);
    })
  }

  selectCinema(cinemaName:String){
    this.cinemaService.findOneByName(cinemaName).subscribe(data => {
      this.cinema = data;
      for(let id of this.cinema.plannedMovies.movieIds){
        this.movieService.findById(id).subscribe(data=>{
          this.movies.set(id,data);
        });
      }
    })
  }

  selectHall(hall:Moviehall){
    this.hall=null;
    this.hall=hall;
    this.events=[];
    this.dayService.findDaysForCinema(this.cinema).subscribe(data => {
      this.days=data;
      for(let d of this.days){
        for(let timeSlot of d.planning[this.hall.hallNumber-1].timeSlots){
          let event:CalendarEvent={"start":new Date(timeSlot.startTime),"end":new Date(timeSlot.stopTime),"title":this.movies.get(timeSlot.movieId).title};
          this.events.push(event);
        }
  }
    });
  }
}
