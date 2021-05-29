import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkScheduleDermatologistComponent } from './work-schedule-dermatologist.component';

describe('WorkScheduleDermatologistComponent', () => {
  let component: WorkScheduleDermatologistComponent;
  let fixture: ComponentFixture<WorkScheduleDermatologistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WorkScheduleDermatologistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkScheduleDermatologistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
