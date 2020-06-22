import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SellerHomeComponent } from './seller-home/seller-home.component';
import { ItemsRefillComponent } from './items-refill/items-refill.component';
import { RoleUserGuard } from '../shared/auth/guards/role-user.guard';


const routes: Routes = [
  { path: '',
  component: SellerHomeComponent,
  canActivate: [RoleUserGuard],
  data: {
    expectedRole: 'ROLE_SELLER'
  },
  children: [
    {
      path: '',
      canActivateChild: [RoleUserGuard],
      children: [
        { path: 'items', component: ItemsRefillComponent},
      ]
    }
  ]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SellerRoutingModule { }
