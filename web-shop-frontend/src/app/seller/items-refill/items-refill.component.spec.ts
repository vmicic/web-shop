import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemsRefillComponent } from './items-refill.component';

describe('ItemsRefillComponent', () => {
  let component: ItemsRefillComponent;
  let fixture: ComponentFixture<ItemsRefillComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemsRefillComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemsRefillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
