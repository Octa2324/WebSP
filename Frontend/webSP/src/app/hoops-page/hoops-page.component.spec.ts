import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HoopsPageComponent } from './hoops-page.component';

describe('HoopsPageComponent', () => {
  let component: HoopsPageComponent;
  let fixture: ComponentFixture<HoopsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HoopsPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HoopsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
