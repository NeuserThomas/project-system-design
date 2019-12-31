import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DayService } from '../../../service/hall-planning-service/day-service.service';
import { Day } from '../../../model/hall-planning-service/day';


@Component({
  selector: 'app-day-form',
  templateUrl: './day-form.component.html',
  styleUrls: ['./day-form.component.css']
})
export class DayFormComponent {

  day: Day;

  constructor(
    private route: ActivatedRoute,
      private router: Router,
        private dayService: DayService) {
    this.day = new Day();
  }

  onSubmit() {
      this.dayService.save(this.day).subscribe(result => this.gotoDayList());
  }

    gotoDayList() {
      this.router.navigate(['/planning']);
    }

}
