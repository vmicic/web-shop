import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserHomeComponent } from './user-home/user-home.component';
import { RoleUserGuard } from '../shared/auth/guards/role-user.guard';


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
