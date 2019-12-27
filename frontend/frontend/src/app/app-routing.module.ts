import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DayListComponent } from './component/common/day-list/day-list.component'
import { DayFormComponent } from './component/common/day-form/day-form.component'
import { CinemaListComponent } from './component/HallPlanning/cinema-list/cinema-list.component'
import { CinemaFormComponent } from './component/HallPlanning/cinema-form/cinema-form.component'
import { MoviehallListComponent } from './component/common/moviehall-list/moviehall-list.component'
import { MoviehallFormComponent } from './component/common/moviehall-form/moviehall-form.component'
import { HallPlanningServiceComponent } from './component/HallPlanning/hall-planning-service/hall-planning-service.component';
import { MovieListComponent } from './component/common/movie-list/movie-list.component';
import { SearchMovieComponent } from './component/HallPlanning/search-movie/search-movie.component';
import { ShowPlanningComponent } from './component/common/show-planning/show-planning.component';
import { TicketServiceComponent } from './component/ticket-service/ticket-service.component';
import { ParkingServiceComponent } from './component/parking-service/parking-service.component';

const routes: Routes = [
  {path: 'hallPlanningService',component: HallPlanningServiceComponent },
  {path: 'ticketService',component: TicketServiceComponent},
  {path: 'parkingService',component: ParkingServiceComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
