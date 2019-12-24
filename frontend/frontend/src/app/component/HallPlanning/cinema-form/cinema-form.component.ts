import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CinemaService } from '../../../service/cinema-service.service';
import { Cinema } from '../../../model/cinema';


@Component({
  selector: 'app-cinema-form',
  templateUrl: './cinema-form.component.html',
  styleUrls: ['./cinema-form.component.css']
})
export class CinemaFormComponent {

  cinema: Cinema;

  constructor(
    private route: ActivatedRoute,
      private router: Router,
        private cinemaService: CinemaService) {
    this.cinema = new Cinema();
  }

  onSubmit() {
      this.cinemaService.save(this.cinema).subscribe(result => this.gotoCinemaList());
  }

    gotoCinemaList() {
      this.router.navigate(['/cinema']);
    }

}
