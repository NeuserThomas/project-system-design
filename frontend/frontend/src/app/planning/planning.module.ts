import { NgModule } from '@angular/core';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { CommonModule } from '@angular/common';
import { CalenderComponent } from '../component/common/calender-component/calendar.component';
import { ShowPlanningComponent } from '../component/common/show-planning/show-planning.component';

@NgModule({
  imports: [
    CommonModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    })
  ],
  declarations: [CalenderComponent],
  exports: [CalenderComponent]
})
export class PlanningModule { }
