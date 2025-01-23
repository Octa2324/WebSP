import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HoopsPixelArtPageComponent } from './hoops-pixel-art-page.component';

describe('HoopsPixelArtPageComponent', () => {
  let component: HoopsPixelArtPageComponent;
  let fixture: ComponentFixture<HoopsPixelArtPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HoopsPixelArtPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HoopsPixelArtPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
