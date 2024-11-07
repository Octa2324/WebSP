import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HalloweenShooterPageComponent } from './halloween-shooter-page.component';

describe('HalloweenShooterPageComponent', () => {
  let component: HalloweenShooterPageComponent;
  let fixture: ComponentFixture<HalloweenShooterPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HalloweenShooterPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HalloweenShooterPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
