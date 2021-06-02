import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPreviousExaminationsProfileComponent } from './view-previous-examinations-profile.component';

describe('ViewPreviousExaminationsProfileComponent', () => {
  let component: ViewPreviousExaminationsProfileComponent;
  let fixture: ComponentFixture<ViewPreviousExaminationsProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPreviousExaminationsProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPreviousExaminationsProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
