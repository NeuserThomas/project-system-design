import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DayListComponent } from './day-list/day-list.component';
import { DayFormComponent } from './day-form/day-form.component';
import { DayService } from './services/day-service.service';

@NgModule({
  declarations: [
    AppComponent,
    DayListComponent,
    DayFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [DayService],
  bootstrap: [AppComponent]
})
export class AppModule { }
