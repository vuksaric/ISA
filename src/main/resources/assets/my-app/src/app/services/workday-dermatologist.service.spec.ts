import { TestBed } from '@angular/core/testing';

import { WorkdayDermatologistService } from './workday-dermatologist.service';

describe('WorkdayDermatologistService', () => {
  let service: WorkdayDermatologistService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkdayDermatologistService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
