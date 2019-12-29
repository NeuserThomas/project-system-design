import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketServiceComponent } from './ticket-service.component';

describe('TicketServiceComponent', () => {
  let component: TicketServiceComponent;
  let fixture: ComponentFixture<TicketServiceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TicketServiceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
