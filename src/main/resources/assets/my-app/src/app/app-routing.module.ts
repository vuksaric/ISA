import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SystemAdminHomePageComponent } from './pages/system-admin-home-page/system-admin-home-page.component';

const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo:'login'},
    { path: 'login', component:LoginPageComponent},
    { path: 'registration', component:RegistrationPageComponent},
    { path: 'sysadminhome', component:SystemAdminHomePageComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }