import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SystemAdminHomePageComponent } from './pages/system-admin-home-page/system-admin-home-page.component';
import { HomePagePharmacistComponent } from './pages/home-page-pharmacist/home-page-pharmacist.component';
import { ProfilePharmacistComponent } from './pages/profile-pharmacist/profile-pharmacist.component';

const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo:'login'},
    { path: 'login', component:LoginPageComponent},
    { path: 'registration', component:RegistrationPageComponent},
    { path: 'sysadminhome', component:SystemAdminHomePageComponent},
    { path: 'homePagePharmacist', component:HomePagePharmacistComponent},
    { path: 'profilePharmacist', component:ProfilePharmacistComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }