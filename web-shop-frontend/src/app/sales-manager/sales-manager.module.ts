import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SalesManagerRoutingModule } from './sales-manager-routing.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DataTablesModule } from 'angular-datatables';
import { ReactiveFormsModule } from '@angular/forms';
import { SalesManagerHomeComponent } from './sales-manager-home/sales-manager-home.component';
import { UserCategoryComponent } from './user-category/user-category.component';
import { ItemCategoryComponent } from './item-category/item-category.component';
import { PromotionsComponent } from './promotions/promotions.component';


@NgModule({
  declarations: [
    SalesManagerHomeComponent,
    UserCategoryComponent,
    ItemCategoryComponent,
    PromotionsComponent
  ],
  imports: [
    CommonModule,
    SalesManagerRoutingModule,
    NgbModule,
    DataTablesModule,
    ReactiveFormsModule
  ]
})
export class SalesManagerModule { }
