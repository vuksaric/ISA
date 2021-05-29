import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewConsultationPharmacistComponent } from './new-consultation-pharmacist.component';

describe('NewConsultationPharmacistComponent', () => {
  let component: NewConsultationPharmacistComponent;
  let fixture: ComponentFixture<NewConsultationPharmacistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewConsultationPharmacistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewConsultationPharmacistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
