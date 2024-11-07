import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { FirstPageComponent } from './first-page/first-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { TestPageComponent } from './skyShooter-page/test-page.component';
import { HalloweenShooterPageComponent } from './halloween-shooter-page/halloween-shooter-page.component';

const routes: Routes = [
  { path : 'first-page', component: FirstPageComponent},
  { path: '', redirectTo : 'first-page',  pathMatch: 'full'},
  { path: 'login-page', component: LoginPageComponent},
  { path: 'register-page', component: RegisterPageComponent},
  { path: 'test-page', component: TestPageComponent},
  { path: 'halloween-page', component: HalloweenShooterPageComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
