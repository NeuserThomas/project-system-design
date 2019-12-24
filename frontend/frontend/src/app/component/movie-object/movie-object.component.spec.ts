import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieObjectComponent } from './movie-object.component';

describe('MovieObjectComponent', () => {
  let component: MovieObjectComponent;
  let fixture: ComponentFixture<MovieObjectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieObjectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieObjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
