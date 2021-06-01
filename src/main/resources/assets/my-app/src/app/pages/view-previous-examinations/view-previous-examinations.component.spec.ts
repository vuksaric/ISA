import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPreviousExaminationsComponent } from './view-previous-examinations.component';

describe('ViewPreviousExaminationsComponent', () => {
  let component: ViewPreviousExaminationsComponent;
  let fixture: ComponentFixture<ViewPreviousExaminationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPreviousExaminationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPreviousExaminationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
