import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemCategoryNewComponent } from './item-category-new.component';

describe('ItemCategoryNewComponent', () => {
  let component: ItemCategoryNewComponent;
  let fixture: ComponentFixture<ItemCategoryNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemCategoryNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemCategoryNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
