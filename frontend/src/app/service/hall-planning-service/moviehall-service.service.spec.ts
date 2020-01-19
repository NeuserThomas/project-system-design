import { TestBed } from '@angular/core/testing';

import { MoviehallService } from './moviehall-service.service';

describe('MoviehallService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MoviehallService = TestBed.get(MoviehallService);
    expect(service).toBeTruthy();
  });
});
