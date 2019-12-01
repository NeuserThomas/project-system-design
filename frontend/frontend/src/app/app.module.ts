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

import { DayService } from './service/day-service.service';
import { CinemaService } from './service/cinema-service.service';

@NgModule({
  declarations: [
    AppComponent,
    DayListComponent,
    DayFormComponent,
    CinemaListComponent,
    CinemaFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [DayService,CinemaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
