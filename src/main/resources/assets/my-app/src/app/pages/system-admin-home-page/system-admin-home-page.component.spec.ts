import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SystemAdminHomePageComponent } from './system-admin-home-page.component';

describe('SystemAdminHomePageComponent', () => {
  let component: SystemAdminHomePageComponent;
  let fixture: ComponentFixture<SystemAdminHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SystemAdminHomePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SystemAdminHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
