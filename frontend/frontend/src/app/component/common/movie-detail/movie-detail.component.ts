import { Component, OnInit, Input, Output } from '@angular/core';
import { Movie } from 'src/app/model/movie';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.css']
})
export class MovieDetailComponent implements OnInit {
  @Input() movie: Movie;
  @Output() onHide: EventEmitter<boolean> = new EventEmitter<boolean>();

  setHide(){
    this.onHide.emit(this.movie===null);
  }
  
  IgnoredParams=["title","id"];
  constructor() { }
  ngOnInit() {}

  shouldBeIgnored(param){
    return this.IgnoredParams.indexOf(param)>0;
  }

  back(){
    this.setHide();
  }
}
