import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewErecipesComponent } from './view-erecipes.component';

describe('ViewErecipesComponent', () => {
  let component: ViewErecipesComponent;
  let fixture: ComponentFixture<ViewErecipesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewErecipesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewErecipesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
