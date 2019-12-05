import { TestBed } from '@angular/core/testing';

import { CinemaServiceService } from './cinema-service.service';

describe('CinemaServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CinemaServiceService = TestBed.get(CinemaServiceService);
    expect(service).toBeTruthy();
  });
});
