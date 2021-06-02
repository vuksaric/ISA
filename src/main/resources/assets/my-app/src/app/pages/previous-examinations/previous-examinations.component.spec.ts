import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviousExaminationsComponent } from './previous-examinations.component';

describe('PreviousExaminationsComponent', () => {
  let component: PreviousExaminationsComponent;
  let fixture: ComponentFixture<PreviousExaminationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreviousExaminationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviousExaminationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
