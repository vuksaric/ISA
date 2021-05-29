import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewConsultationPatientComponent } from './new-consultation-patient.component';

describe('NewConsultationPatientComponent', () => {
  let component: NewConsultationPatientComponent;
  let fixture: ComponentFixture<NewConsultationPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewConsultationPatientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewConsultationPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
