import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicineOrderComponent } from './medicine-order.component';

describe('MedicineOrderComponent', () => {
  let component: MedicineOrderComponent;
  let fixture: ComponentFixture<MedicineOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicineOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicineOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
