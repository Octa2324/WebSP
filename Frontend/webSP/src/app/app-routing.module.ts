import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { FirstPageComponent } from './first-page/first-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { TestPageComponent } from './skyShooter-page/test-page.component';
import { HalloweenShooterPageComponent } from './halloween-shooter-page/halloween-shooter-page.component';
import { AccountPageComponent } from './account-page/account-page.component';
import { AuthGuard } from './Services/auth-guard';
import { HoopsPageComponent } from './hoops-page/hoops-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { HoopsPixelArtPageComponent } from './hoops-pixel-art-page/hoops-pixel-art-page.component';
import { BaseBuiderPageComponent } from './base-buider-page/base-buider-page.component';

const routes: Routes = [
  { path: 'first-page', component: FirstPageComponent },
  { path: '', redirectTo: 'first-page', pathMatch: 'full' },
  { path: 'login-page', component: LoginPageComponent },
  { path: 'register-page', component: RegisterPageComponent },
  { path: 'test-page', component: TestPageComponent },
  { path: 'halloween-page', component: HalloweenShooterPageComponent },
  { path: 'hoops-page', component: HoopsPageComponent},
  { path: 'hoopsPixel-page', component: HoopsPixelArtPageComponent},
  { path: 'baseBuider-page', component: BaseBuiderPageComponent},
  { 
    path: 'account-page', 
    component: AccountPageComponent, 
    canActivate: [AuthGuard] 
  },
  {
    path: 'profile-page/:email',
    component: ProfilePageComponent,
    canActivate: [AuthGuard]
  },
  { path: '**', redirectTo: '/first-page' },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
