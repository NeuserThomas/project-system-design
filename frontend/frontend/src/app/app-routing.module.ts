import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DayListComponent } from './component/day-list/day-list.component'
import { DayFormComponent } from './component/day-form/day-form.component'
import { CinemaListComponent } from './component/cinema-list/cinema-list.component'
import { CinemaFormComponent } from './component/cinema-form/cinema-form.component'
import { MoviehallListComponent } from './component/moviehall-list/moviehall-list.component'
import { MoviehallFormComponent } from './component/moviehall-form/moviehall-form.component'
import { HallPlanningServiceComponent } from './component/hall-planning-service/hall-planning-service.component';
import { MovieListComponent } from './component/movie-list/movie-list.component';

const routes: Routes = [
  {path: 'days',component: DayListComponent },
  {path: 'addDay',component: DayFormComponent },
  {path: 'cinemas',component: CinemaListComponent },
  {path: 'addCinema',component: CinemaFormComponent },
  {path: 'moviehalls',component: MoviehallListComponent },
  {path: 'addmoviehall',component: MoviehallFormComponent },
  {path: 'hallPlanningService',component: HallPlanningServiceComponent },
  {path: 'movie',component: MovieListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
