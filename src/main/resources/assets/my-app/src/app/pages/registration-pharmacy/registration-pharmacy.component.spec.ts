import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationPharmacyComponent } from './registration-pharmacy.component';

describe('RegistrationPharmacyComponent', () => {
  let component: RegistrationPharmacyComponent;
  let fixture: ComponentFixture<RegistrationPharmacyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationPharmacyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationPharmacyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
