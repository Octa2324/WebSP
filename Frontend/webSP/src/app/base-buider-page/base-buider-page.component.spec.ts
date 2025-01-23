import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BaseBuiderPageComponent } from './base-buider-page.component';

describe('BaseBuiderPageComponent', () => {
  let component: BaseBuiderPageComponent;
  let fixture: ComponentFixture<BaseBuiderPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BaseBuiderPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BaseBuiderPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
