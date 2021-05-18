import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviousConsultationsComponent } from './previous-consultations.component';

describe('PreviousConsultationsComponent', () => {
  let component: PreviousConsultationsComponent;
  let fixture: ComponentFixture<PreviousConsultationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreviousConsultationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviousConsultationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
