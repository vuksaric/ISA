import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationFrontpageComponent } from './examination-frontpage.component';

describe('ExaminationFrontpageComponent', () => {
  let component: ExaminationFrontpageComponent;
  let fixture: ComponentFixture<ExaminationFrontpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExaminationFrontpageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExaminationFrontpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
