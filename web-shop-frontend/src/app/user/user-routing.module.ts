import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserHomeComponent } from './user-home/user-home.component';
import { RoleUserGuard } from '../shared/auth/guards/role-user.guard';
import { UserItemsComponent } from './user-items/user-items.component';
import { UserCartComponent } from './user-cart/user-cart.component';


const routes: Routes = [
  { path: '',
    component: UserHomeComponent,
    canActivate: [RoleUserGuard],
    data: {
      expectedRole: 'ROLE_USER'
    },
    children: [
      {
        path: '',
        canActivateChild: [RoleUserGuard],
        children: [
          { path: 'items', component: UserItemsComponent},
          { path: 'cart', component: UserCartComponent}
        ]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
