import { TestBed } from '@angular/core/testing';

import { WorkdayPharmacistService } from './workday-pharmacist.service';

describe('WorkdayPharmacistService', () => {
  let service: WorkdayPharmacistService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkdayPharmacistService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
