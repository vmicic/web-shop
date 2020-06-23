import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl, FormArray } from '@angular/forms';
import { Router } from '@angular/router';
import { PromotionService } from '../services/promotion.service';
import { ItemCategoryService } from '../services/item-category.service';

@Component({
  selector: 'app-promotion-new',
  templateUrl: './promotion-new.component.html',
  styleUrls: ['./promotion-new.component.css']
})
export class PromotionNewComponent implements OnInit {

  newPromotionForm: FormGroup;

  itemCategories: any[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private promotionService: PromotionService,
    private itemCategoryService: ItemCategoryService
  ) { }

  ngOnInit() {

    this.newPromotionForm = this.formBuilder.group({
      startTime: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      endTime: ['', Validators.required],
      percentageDiscount: ['', Validators.required],
      name: ['', Validators.required],
      categories: new FormArray([])
    })

    this.itemCategoryService.getAllCategories().subscribe(
      response => {
        this.itemCategories = response.body;
        this.itemCategories.forEach((o, i) => {
          const control = new FormControl();
          (this.newPromotionForm.controls.categories as FormArray).push(control);
        })
      }
    )
  }

  onSubmit() {
    let dateHoursFrom = this.newPromotionForm.controls["startTime"].value.hour;
    let dateHoursFromFormatted = ("0" + dateHoursFrom).slice(-2);

    let dateMinutesFrom = this.newPromotionForm.controls["startTime"].value.minute;
    let dateMinutesFromFormatted = ("0" + dateMinutesFrom).slice(-2);

    let monthFrom = this.newPromotionForm.controls["startDate"].value.month;
    let monthFromFormatted = ("0" + monthFrom).slice(-2);

    let dayFrom = this.newPromotionForm.controls["startDate"].value.day;
    let dayFromFormatted = ("0" + dayFrom).slice(-2);

    let dateStringFrom = this.newPromotionForm.controls["startDate"].value.year + '-' + monthFromFormatted +
      '-' + dayFromFormatted + ' ' + dateHoursFromFormatted + ':' + dateMinutesFromFormatted + ':00';

    let dateHoursTo = this.newPromotionForm.controls["endTime"].value.hour;
    let dateHoursToFormatted = ("0" + dateHoursTo).slice(-2);

    let dateMinutesTo = this.newPromotionForm.controls["endTime"].value.minute;
    let dateMinutesToFormatted = ("0" + dateMinutesTo).slice(-2);


    let monthTo = this.newPromotionForm.controls["endDate"].value.month;
    let monthToFormatted = ("0" + monthTo).slice(-2);

    let dayTo = this.newPromotionForm.controls["endDate"].value.day;
    let dayToFormatted = ("0" + dayTo).slice(-2);

    let dateStringTo = this.newPromotionForm.controls["endDate"].value.year + '-' + monthToFormatted +
      '-' + dayToFormatted + ' ' + dateHoursToFormatted + ':' + dateMinutesToFormatted + ':00';


    const selectedCategoriesIds = this.newPromotionForm.value.categories
      .map((v, i) => (v ? this.itemCategories[i].id : null))
      .filter(v => v !== null);
    console.log(selectedCategoriesIds);


    var promotion = new Object;
    promotion["startTime"] = dateStringFrom;
    promotion["endTime"] = dateStringTo;
    promotion["percentageDiscount"] = this.newPromotionForm.controls["percentageDiscount"].value;
    promotion["name"] = this.newPromotionForm.controls["name"].value;
    promotion["categoryIds"] = selectedCategoriesIds;

    console.log(promotion);

    this.promotionService.createPromotion(promotion).subscribe(
      response => {
        this.router.navigate(["manager/promotion"]);
      }
    )
  }
  

}
