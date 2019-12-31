import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HallPlanningServiceComponent } from './component/hall-planning-service/hall-planning-service/hall-planning-service.component';
import { TicketServiceComponent } from './component/ticket-service/ticket-service.component';
import { ParkingServiceComponent } from './component/parking-service/parking-service.component';
import { ShopServiceComponent } from './component/shop-service/shop-service/shop-service.component';

const routes: Routes = [
  {path: 'hallPlanningService',component: HallPlanningServiceComponent },
  {path: 'ticketService',component: TicketServiceComponent},
  {path: 'parkingService',component: ParkingServiceComponent},
  {path: 'shopService',component: ShopServiceComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
