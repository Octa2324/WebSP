import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { FirstPageComponent } from './first-page/first-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { HttpClientModule } from '@angular/common/http';
import { TestPageComponent } from './skyShooter-page/test-page.component';
import { HalloweenShooterPageComponent } from './halloween-shooter-page/halloween-shooter-page.component';
import { AccountPageComponent } from './account-page/account-page.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { AuthGuard } from './Services/auth-guard';
import { HoopsPageComponent } from './hoops-page/hoops-page.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    FirstPageComponent,
    RegisterPageComponent,
    TestPageComponent,
    HalloweenShooterPageComponent,
    AccountPageComponent,
    AdminPageComponent,
    HoopsPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot([], { useHash: true })
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
