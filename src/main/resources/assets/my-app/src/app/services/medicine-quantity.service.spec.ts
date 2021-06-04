import { TestBed } from '@angular/core/testing';

import { MedicineQuantityService } from './medicine-quantity.service';

describe('MedicineQuantityService', () => {
  let service: MedicineQuantityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicineQuantityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
