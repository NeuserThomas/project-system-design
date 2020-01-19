import { Component, OnInit } from '@angular/core';
import { PublicityService } from 'src/app/service/publicity-service/publicity-service.service';

@Component({
  selector: 'app-publicity-service',
  templateUrl: './publicity-service.component.html',
  styleUrls: ['./publicity-service.component.css']
})
export class PublicityServiceComponent implements OnInit {
  adMovieInfo: string;
  showAdMovieInfo: boolean;
  maxDuration: string;
  showMaxDuration: boolean;
  private category: string;

  constructor(private publicityService: PublicityService) {
    this.showAdMovieInfo = false;
    this.showMaxDuration = false;
  }

  ngOnInit() {
  }

  public getAdMovieByCategory(){
    console.log(this.category);
    this.publicityService.getAdMovieByCategory(this.category).subscribe(res => {
      console.log(res);
      this.showAdMovieInfo = true;
      this.adMovieInfo = 'ID : ' + res.id + ' commissioningDate : ' + res.commissioningDate.toString()
        + ' category : ' + res.category + ' duration : ' + res.duration.toString();
    });
  }

  public getMaxDuration(){
    this.publicityService.getMaxDuration().subscribe(res => {
      console.log(res);
      this.showMaxDuration = true;
      this.maxDuration = res.toString();
    });
  }
}
