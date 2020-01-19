import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicityServiceComponent } from './publicity-service.component';

describe('PublicityServiceComponent', () => {
  let component: PublicityServiceComponent;
  let fixture: ComponentFixture<PublicityServiceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PublicityServiceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PublicityServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
