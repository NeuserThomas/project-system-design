import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoviehallFormComponent } from './moviehall-form.component';

describe('MoviehallFormComponent', () => {
  let component: MoviehallFormComponent;
  let fixture: ComponentFixture<MoviehallFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoviehallFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoviehallFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
