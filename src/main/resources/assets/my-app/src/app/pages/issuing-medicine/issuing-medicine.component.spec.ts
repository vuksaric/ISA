import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssuingMedicineComponent } from './issuing-medicine.component';

describe('IssuingMedicineComponent', () => {
  let component: IssuingMedicineComponent;
  let fixture: ComponentFixture<IssuingMedicineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IssuingMedicineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IssuingMedicineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
