import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilePharmacistComponent } from './profile-pharmacist.component';

describe('ProfilePharmacistComponent', () => {
  let component: ProfilePharmacistComponent;
  let fixture: ComponentFixture<ProfilePharmacistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfilePharmacistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfilePharmacistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
