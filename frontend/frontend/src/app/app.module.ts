import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { DayListComponent } from './component/common/day-list/day-list.component';
import { DayFormComponent } from './component/common/day-form/day-form.component';
import { CinemaListComponent } from './component/HallPlanning/cinema-list/cinema-list.component';
import { CinemaFormComponent } from './component/HallPlanning/cinema-form/cinema-form.component';
import { MoviehallFormComponent } from './component/common/moviehall-form/moviehall-form.component';
import { MoviehallListComponent } from './component/common/moviehall-list/moviehall-list.component';

import { DayService } from './service/day-service.service';
import { CinemaService } from './service/cinema-service.service';
import { MoviehallService } from './service/moviehall-service.service';
import { HallPlanningServiceComponent } from './component/HallPlanning/hall-planning-service/hall-planning-service.component';
import { MovieListComponent } from './component/common/movie-list/movie-list.component';
import { MovieService } from './service/movie-service.service';
import { MovieDetailComponent } from './component/common/movie-detail/movie-detail.component';
import { SearchMovieComponent } from './component/HallPlanning/search-movie/search-movie.component';


@NgModule({
  declarations: [
    AppComponent,
    DayListComponent,
    DayFormComponent,
    CinemaListComponent,
    CinemaFormComponent,
    MoviehallFormComponent,
    MoviehallListComponent,
    HallPlanningServiceComponent,
    MovieListComponent,
    MovieDetailComponent,
    SearchMovieComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [DayService,CinemaService,MoviehallService,MovieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
