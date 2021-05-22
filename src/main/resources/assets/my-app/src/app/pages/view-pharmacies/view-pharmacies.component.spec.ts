import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPharmaciesComponent } from './view-pharmacies.component';

describe('ViewPharmaciesComponent', () => {
  let component: ViewPharmaciesComponent;
  let fixture: ComponentFixture<ViewPharmaciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPharmaciesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPharmaciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
