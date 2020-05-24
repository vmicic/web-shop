import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserHomeComponent } from './user-home/user-home.component';
import { UserItemsComponent } from './user-items/user-items.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import {DataTablesModule} from 'angular-datatables';


@NgModule({
  declarations: [UserHomeComponent, UserItemsComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    DataTablesModule
  ]
})
export class UserModule { }
