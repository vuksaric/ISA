import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewExaminationDermatologistComponent } from './new-examination-dermatologist.component';

describe('NewExaminationDermatologistComponent', () => {
  let component: NewExaminationDermatologistComponent;
  let fixture: ComponentFixture<NewExaminationDermatologistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewExaminationDermatologistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewExaminationDermatologistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
