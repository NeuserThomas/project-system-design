import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoviehallListComponent } from './moviehall-list.component';

describe('MoviehallListComponent', () => {
  let component: MoviehallListComponent;
  let fixture: ComponentFixture<MoviehallListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoviehallListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoviehallListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
