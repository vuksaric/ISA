import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePagePharmacyAdministratorComponent } from './home-page-pharmacy-administrator.component';

describe('HomePagePharmacyAdministratorComponent', () => {
  let component: HomePagePharmacyAdministratorComponent;
  let fixture: ComponentFixture<HomePagePharmacyAdministratorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomePagePharmacyAdministratorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomePagePharmacyAdministratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
