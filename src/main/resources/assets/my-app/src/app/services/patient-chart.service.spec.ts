import { TestBed } from '@angular/core/testing';

import { PatientChartService } from './patient-chart.service';

describe('PatientChartService', () => {
  let service: PatientChartService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientChartService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
