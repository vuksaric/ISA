import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePageSupplierComponent } from './home-page-supplier.component';

describe('HomePageSupplierComponent', () => {
  let component: HomePageSupplierComponent;
  let fixture: ComponentFixture<HomePageSupplierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomePageSupplierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomePageSupplierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
