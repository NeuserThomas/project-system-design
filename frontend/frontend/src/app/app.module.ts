import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { DayListComponent } from './component/day-list/day-list.component';
import { DayFormComponent } from './component/day-form/day-form.component';
import { CinemaListComponent } from './component/cinema-list/cinema-list.component';
import { CinemaFormComponent } from './component/cinema-form/cinema-form.component';
import { MoviehallFormComponent } from './component/moviehall-form/moviehall-form.component';
import { MoviehallListComponent } from './component/moviehall-list/moviehall-list.component';

import { DayService } from './service/day-service.service';
import { CinemaService } from './service/cinema-service.service';
import { MoviehallService } from './service/moviehall-service.service';
import { HallPlanningServiceComponent } from './component/hall-planning-service/hall-planning-service.component';
import { MovieListComponent } from './component/movie-list/movie-list.component';
import { MovieObjectComponent } from './component/movie-object/movie-object.component';
import { MovieService } from './service/movie-service.service';


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
    MovieObjectComponent
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
