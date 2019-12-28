import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MoviehallService } from '../../../service/hall-planning-service/moviehall-service.service';
import { Moviehall } from '../../../model/hall-planning-service/moviehall';


@Component({
  selector: 'app-moviehall-form',
  templateUrl: './moviehall-form.component.html',
  styleUrls: ['./moviehall-form.component.css']
})
export class MoviehallFormComponent {

  moviehall: Moviehall;

  constructor(
    private route: ActivatedRoute,
      private router: Router,
        private moviehallService: MoviehallService) {
    this.moviehall = new Moviehall();
  }

  onSubmit() {
      this.moviehallService.save(this.moviehall).subscribe(result => this.gotoMoviehallList());
  }

    gotoMoviehallList() {
      this.router.navigate(['/hall']);
    }

}
