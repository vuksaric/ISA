import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFutureExaminationsComponent } from './view-future-examinations.component';

describe('ViewFutureExaminationsComponent', () => {
  let component: ViewFutureExaminationsComponent;
  let fixture: ComponentFixture<ViewFutureExaminationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewFutureExaminationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFutureExaminationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
