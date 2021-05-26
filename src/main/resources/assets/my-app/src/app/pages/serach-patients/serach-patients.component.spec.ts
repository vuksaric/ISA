import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SerachPatientsComponent } from './serach-patients.component';

describe('SerachPatientsComponent', () => {
  let component: SerachPatientsComponent;
  let fixture: ComponentFixture<SerachPatientsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SerachPatientsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SerachPatientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
