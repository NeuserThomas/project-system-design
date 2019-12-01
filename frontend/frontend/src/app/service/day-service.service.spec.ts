import { TestBed } from '@angular/core/testing';

import { DayServiceService } from './day-service.service';

describe('DayServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DayServiceService = TestBed.get(DayServiceService);
    expect(service).toBeTruthy();
  });
});
