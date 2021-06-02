import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewErecipeMedicinesComponent } from './view-erecipe-medicines.component';

describe('ViewErecipeMedicinesComponent', () => {
  let component: ViewErecipeMedicinesComponent;
  let fixture: ComponentFixture<ViewErecipeMedicinesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewErecipeMedicinesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewErecipeMedicinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
