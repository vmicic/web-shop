import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SalesManagerHomeComponent } from './sales-manager-home/sales-manager-home.component';
import { RoleUserGuard } from '../shared/auth/guards/role-user.guard';
import { UserCategoryComponent } from './user-category/user-category.component';
import { ItemCategoryComponent } from './item-category/item-category.component';
import { PromotionsComponent } from './promotions/promotions.component';
import { PromotionNewComponent } from './promotion-new/promotion-new.component';


const routes: Routes = [
  { path: '',
  component: SalesManagerHomeComponent,
  canActivate: [RoleUserGuard],
  data: {
    expectedRole: 'ROLE_SALES_MANAGER'
  },
  children: [
    {
      path: '',
      canActivateChild: [RoleUserGuard],
      children: [
        { path: "user-category", component: UserCategoryComponent},
        { path: "item-category", component: ItemCategoryComponent},
        { path: "promotion/new", component: PromotionNewComponent},
        { path: "promotion", component: PromotionsComponent}
      ]
    }
  ]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SalesManagerRoutingModule { }
