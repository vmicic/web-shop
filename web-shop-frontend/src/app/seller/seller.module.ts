import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SellerRoutingModule } from './seller-routing.module';
import { SellerHomeComponent } from './seller-home/seller-home.component';
import { ItemsRefillComponent } from './items-refill/items-refill.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { DataTablesModule } from 'angular-datatables';


@NgModule({
  declarations: [SellerHomeComponent, ItemsRefillComponent],
  imports: [
    CommonModule,
    SellerRoutingModule,
    NgbModule,
    DataTablesModule
  ]
})
export class SellerModule { }
