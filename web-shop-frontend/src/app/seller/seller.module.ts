import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SellerRoutingModule } from './seller-routing.module';
import { SellerHomeComponent } from './seller-home/seller-home.component';
import { ItemsRefillComponent } from './items-refill/items-refill.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { DataTablesModule } from 'angular-datatables';
import { ReactiveFormsModule } from '@angular/forms';
import { OrdersComponent } from './orders/orders.component';


@NgModule({
  declarations: [SellerHomeComponent, ItemsRefillComponent, OrdersComponent],
  imports: [
    CommonModule,
    SellerRoutingModule,
    NgbModule,
    DataTablesModule,
    ReactiveFormsModule
  ]
})
export class SellerModule { }
