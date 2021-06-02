import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFutureConsultationsComponent } from './view-future-consultations.component';

describe('ViewFutureConsultationsComponent', () => {
  let component: ViewFutureConsultationsComponent;
  let fixture: ComponentFixture<ViewFutureConsultationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewFutureConsultationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFutureConsultationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
