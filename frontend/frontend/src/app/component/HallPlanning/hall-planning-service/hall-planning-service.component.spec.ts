import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HallPlanningServiceComponent } from './hall-planning-service.component';

describe('HallPlanningServiceComponent', () => {
  let component: HallPlanningServiceComponent;
  let fixture: ComponentFixture<HallPlanningServiceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HallPlanningServiceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HallPlanningServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
