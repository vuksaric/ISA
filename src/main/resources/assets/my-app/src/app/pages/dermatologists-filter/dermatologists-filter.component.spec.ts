import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistsFilterComponent } from './dermatologists-filter.component';

describe('DermatologistsFilterComponent', () => {
  let component: DermatologistsFilterComponent;
  let fixture: ComponentFixture<DermatologistsFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistsFilterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistsFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
