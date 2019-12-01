import { TestBed } from '@angular/core/testing';

import { MoviehallServiceService } from './moviehall-service.service';

describe('MoviehallServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MoviehallServiceService = TestBed.get(MoviehallServiceService);
    expect(service).toBeTruthy();
  });
});
