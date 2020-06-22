import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SalesManagerHomeComponent } from './sales-manager-home.component';

describe('SalesManagerHomeComponent', () => {
  let component: SalesManagerHomeComponent;
  let fixture: ComponentFixture<SalesManagerHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SalesManagerHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SalesManagerHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
