import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParkingServiceComponent } from './parking-service.component';

describe('ParkingServiceComponent', () => {
  let component: ParkingServiceComponent;
  let fixture: ComponentFixture<ParkingServiceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParkingServiceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParkingServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
