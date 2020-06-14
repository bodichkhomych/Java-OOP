import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { PaymentComponent } from './user/payment/payment.component';
import { ReplenishComponent } from './user/replenish/replenish.component';


const routes: Routes = [
  {
    path: 'user_profile',
    component: UserProfileComponent
  },
  {
    path: 'payment',
    component: PaymentComponent
  }, {
    path: 'replenish',
    component: ReplenishComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
