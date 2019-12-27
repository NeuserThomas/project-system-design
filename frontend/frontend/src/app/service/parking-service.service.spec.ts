import { TestBed } from '@angular/core/testing';

import { ParkingServiceService } from './parking-service.service';

describe('ParkingServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ParkingServiceService = TestBed.get(ParkingServiceService);
    expect(service).toBeTruthy();
  });
});
