import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacationApprovalComponent } from './vacation-approval.component';

describe('VacationApprovalComponent', () => {
  let component: VacationApprovalComponent;
  let fixture: ComponentFixture<VacationApprovalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VacationApprovalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacationApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
