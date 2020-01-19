import { TestBed } from '@angular/core/testing';

import { PublicityService } from './publicity-service.service';

describe('PublicityService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PublicityService = TestBed.get(PublicityService);
    expect(service).toBeTruthy();
  });
});
