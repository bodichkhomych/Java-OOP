import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { FlexModule, FlexLayoutModule } from "@angular/flex-layout";

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatListModule } from '@angular/material/list';

import { initializer } from 'src/utils/app-init';
import { LoginComponent } from './login/login.component';
import { StartComponent } from './start/start.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { PaymentComponent } from './user/payment/payment.component';
import { ReplenishComponent } from './user/replenish/replenish.component';
import { AdminProfileComponent } from './admin/admin-profile/admin-profile.component';
import { ManageCardsComponent } from './admin/manage-cards/manage-cards.component';
import { CardsComponent } from './user/cards/cards.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    StartComponent,
    UserProfileComponent,
    PaymentComponent,
    ReplenishComponent,
    AdminProfileComponent,
    ManageCardsComponent,
    CardsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    KeycloakAngularModule,
    MatCardModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule,
    MatPaginatorModule,
    FlexModule,
    FlexLayoutModule,
    RouterModule
  ],
  // providers: [
  //   {
  //     provide: APP_INITIALIZER,
  //     useFactory: initializer,
  //     deps: [KeycloakService],
  //     multi: true
  //   }
  // ],
  bootstrap: [AppComponent]
})

export class AppModule { }
