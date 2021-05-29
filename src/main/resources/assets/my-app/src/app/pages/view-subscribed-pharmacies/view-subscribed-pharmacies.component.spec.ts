import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewSubscribedPharmaciesComponent } from './view-subscribed-pharmacies.component';

describe('ViewSubscribedPharmaciesComponent', () => {
  let component: ViewSubscribedPharmaciesComponent;
  let fixture: ComponentFixture<ViewSubscribedPharmaciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewSubscribedPharmaciesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewSubscribedPharmaciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
