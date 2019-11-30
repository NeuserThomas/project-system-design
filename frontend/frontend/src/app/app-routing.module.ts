import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DayListComponent } from './day-list/day-list.component'
import { DayFormComponent } from './day-form/day-form.component'

const routes: Routes = [
  {path: 'days',component: DayListComponent },
  {path: 'addDay',component: DayFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
