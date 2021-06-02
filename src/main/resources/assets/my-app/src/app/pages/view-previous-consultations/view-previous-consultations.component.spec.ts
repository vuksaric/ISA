import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPreviousConsultationsComponent } from './view-previous-consultations.component';

describe('ViewPreviousConsultationsComponent', () => {
  let component: ViewPreviousConsultationsComponent;
  let fixture: ComponentFixture<ViewPreviousConsultationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPreviousConsultationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPreviousConsultationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
