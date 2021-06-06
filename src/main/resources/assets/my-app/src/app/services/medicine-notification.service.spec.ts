import { TestBed } from '@angular/core/testing';

import { MedicineNotificationService } from './medicine-notification.service';

describe('MedicineNotificationService', () => {
  let service: MedicineNotificationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicineNotificationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
