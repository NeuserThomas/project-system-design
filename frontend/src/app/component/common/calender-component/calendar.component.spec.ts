import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CalenderComponent } from './calendar.component';

describe('ShowPlanningComponent', () => {
  let component: CalenderComponent;
  let fixture: ComponentFixture<CalenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CalenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CalenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
