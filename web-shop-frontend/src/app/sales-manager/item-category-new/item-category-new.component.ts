import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ItemCategoryService } from '../services/item-category.service';

@Component({
  selector: 'app-item-category-new',
  templateUrl: './item-category-new.component.html',
  styleUrls: ['./item-category-new.component.css']
})
export class ItemCategoryNewComponent implements OnInit {


  newCategoryForm: FormGroup;

  constructor(
  private formBuilder: FormBuilder,
  private router: Router,
  private itemCategoryService: ItemCategoryService
  ) { }

ngOnInit() {

  this.newCategoryForm = this.formBuilder.group({
    name: ['', Validators.required],
    maxPercentageDiscount: ['', Validators.required]
  })
}

onSubmit() {
  this.itemCategoryService.create(this.newCategoryForm.value).subscribe(
    reponse => {
      this.router.navigate(["manager/item-category"]);
    }
  )
}

}
