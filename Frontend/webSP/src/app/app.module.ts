import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { FirstPageComponent } from './first-page/first-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { HttpClientModule } from '@angular/common/http';
import { TestPageComponent } from './skyShooter-page/test-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    FirstPageComponent,
    RegisterPageComponent,
    TestPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
