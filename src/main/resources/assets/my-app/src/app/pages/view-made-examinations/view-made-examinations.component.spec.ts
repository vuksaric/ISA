import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewMadeExaminationsComponent } from './view-made-examinations.component';

describe('ViewMadeExaminationsComponent', () => {
  let component: ViewMadeExaminationsComponent;
  let fixture: ComponentFixture<ViewMadeExaminationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewMadeExaminationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewMadeExaminationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
