import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultationFrontpageComponent } from './consultation-frontpage.component';

describe('ConsultationFrontpageComponent', () => {
  let component: ConsultationFrontpageComponent;
  let fixture: ComponentFixture<ConsultationFrontpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultationFrontpageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultationFrontpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
