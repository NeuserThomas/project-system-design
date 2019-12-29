import { TestBed } from '@angular/core/testing';

import { DayService } from './day.service';

describe('DayServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PlanningServiceService = TestBed.get(PlanningServiceService);
    expect(service).toBeTruthy();
  });
});
