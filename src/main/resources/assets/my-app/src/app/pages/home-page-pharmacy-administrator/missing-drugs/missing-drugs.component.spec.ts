import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MissingDrugsComponent } from './missing-drugs.component';

describe('MissingDrugsComponent', () => {
  let component: MissingDrugsComponent;
  let fixture: ComponentFixture<MissingDrugsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MissingDrugsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MissingDrugsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
