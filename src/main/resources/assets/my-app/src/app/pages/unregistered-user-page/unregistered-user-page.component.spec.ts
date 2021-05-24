import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnregisteredUserPageComponent } from './unregistered-user-page.component';

describe('UnregisteredUserPageComponent', () => {
  let component: UnregisteredUserPageComponent;
  let fixture: ComponentFixture<UnregisteredUserPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnregisteredUserPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnregisteredUserPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
