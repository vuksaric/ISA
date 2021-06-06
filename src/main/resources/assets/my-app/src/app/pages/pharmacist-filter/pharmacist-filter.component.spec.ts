import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistFilterComponent } from './pharmacist-filter.component';

describe('PharmacistFilterComponent', () => {
  let component: PharmacistFilterComponent;
  let fixture: ComponentFixture<PharmacistFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistFilterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
