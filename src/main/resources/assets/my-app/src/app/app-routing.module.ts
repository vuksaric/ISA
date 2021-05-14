import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageUserComponent } from './pages/home-page-user/home-page-user.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import { HomePagePharmacistComponent } from './pages/home-page-pharmacist/home-page-pharmacist.component';
import { ProfilePharmacistComponent } from './pages/profile-pharmacist/profile-pharmacist.component';

const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo:'login'},
    { path: 'login', component:LoginPageComponent},
    { path: 'registration', component:RegistrationPageComponent},
    { path: 'homepage', component: HomePageUserComponent},
    { path: 'userProfile', component:UserProfileComponent},
    { path: 'homePagePharmacist', component:HomePagePharmacistComponent},
    { path: 'profilePharmacist', component:ProfilePharmacistComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }