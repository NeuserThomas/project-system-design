import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { DayListComponent } from './component/common/day-list/day-list.component';
import { DayFormComponent } from './component/common/day-form/day-form.component';
import { CinemaListComponent } from './component/hall-planning-service/cinema-list/cinema-list.component';
import { CinemaFormComponent } from './component/hall-planning-service/cinema-form/cinema-form.component';
import { MoviehallFormComponent } from './component/common/moviehall-form/moviehall-form.component';
import { MoviehallListComponent } from './component/common/moviehall-list/moviehall-list.component';

import { DayService } from './service/hall-planning-service/day-service.service';
import { CinemaService } from './service/hall-planning-service/cinema-service.service';
import { MoviehallService } from './service/hall-planning-service/moviehall-service.service';
import { HallPlanningServiceComponent } from './component/hall-planning-service/hall-planning-service/hall-planning-service.component';
import { MovieListComponent } from './component/common/movie-list/movie-list.component';
import { MovieService } from './service/hall-planning-service/movie-service.service';
import { MovieDetailComponent } from './component/common/movie-detail/movie-detail.component';
import { SearchMovieComponent } from './component/hall-planning-service/search-movie/search-movie.component';
import { PlanningModule } from './planning/planning.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TicketServiceComponent } from './component/ticket-service/ticket-service.component';
import { ParkingServiceComponent } from './component/parking-service/parking-service.component';
import { ShowPlanningComponent } from './component/common/show-planning/show-planning.component';
import { ShopServiceComponent } from './component/shop-service/shop-service/shop-service.component';
import { ListStockComponent } from './component/shop-service/list-stock/list-stock.component';
import { ShopItemListComponent } from './component/shop-service/shop-item-list/shop-item-list.component';
import { NewTransactionComponent } from './component/shop-service/new-transaction/new-transaction.component';


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
    SearchMovieComponent,
    TicketServiceComponent,
    ParkingServiceComponent,
    ShowPlanningComponent,
    ShopServiceComponent,
    ListStockComponent,
    ShopItemListComponent,
    NewTransactionComponent
    ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    PlanningModule
  ],
  providers: [DayService,CinemaService,MoviehallService,MovieService],
  bootstrap: [AppComponent]
})
export class AppModule { }