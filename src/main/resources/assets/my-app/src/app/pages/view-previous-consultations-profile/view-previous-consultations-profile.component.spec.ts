import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPreviousConsultationsProfileComponent } from './view-previous-consultations-profile.component';

describe('ViewPreviousConsultationsProfileComponent', () => {
  let component: ViewPreviousConsultationsProfileComponent;
  let fixture: ComponentFixture<ViewPreviousConsultationsProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPreviousConsultationsProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPreviousConsultationsProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
