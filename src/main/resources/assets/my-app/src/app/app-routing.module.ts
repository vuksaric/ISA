import { PharmacistListComponent } from './pages/home-page-pharmacy-administrator/pharmacist-list/pharmacist-list.component';
import { MedicineOrderComponent } from './pages/home-page-pharmacy-administrator/medicine-order/medicine-order.component';
import { MedicineListComponent } from './pages/home-page-pharmacy-administrator/medicine-list/medicine-list.component';
import { PharmacyReportComponent } from './pages/home-page-pharmacy-administrator/pharmacy-report/pharmacy-report.component';
import { PharmacyProfileComponent } from './pages/home-page-pharmacy-administrator/pharmacy-profile/pharmacy-profile.component';
import { HomePagePharmacyAdministratorComponent } from './pages/home-page-pharmacy-administrator/home-page-pharmacy-administrator.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePagePharmacistComponent } from './pages/home-page-pharmacist/home-page-pharmacist.component';
import { ProfilePharmacistComponent } from './pages/profile-pharmacist/profile-pharmacist.component';

const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo: 'pharmacyAdmin' },
    { path: 'login', component: LoginPageComponent },
    { path: 'registration', component: RegistrationPageComponent },
    { path: 'homePagePharmacist', component: HomePagePharmacistComponent },
    { path: 'profilePharmacist', component: ProfilePharmacistComponent },
    { path: 'pharmacyAdmin', component: HomePagePharmacyAdministratorComponent, children: [
        { path: 'pharmacy-profile', component: PharmacyProfileComponent},
        { path: 'pharmacy-report', component: PharmacyReportComponent },
        { path: 'medicine-list', component: MedicineListComponent },
        { path: 'medicine-order', component: MedicineOrderComponent},
        { path: 'pharmacist-list', component: PharmacistListComponent }
    ] },
    
]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }