import { PharmacistListComponent } from './pages/home-page-pharmacy-administrator/pharmacist-list/pharmacist-list.component';
import { MedicineListComponent } from './pages/home-page-pharmacy-administrator/medicine-list/medicine-list.component';
import { MedicineOrderComponent } from './pages/home-page-pharmacy-administrator/medicine-order/medicine-order.component';
import { PharmacyReportComponent } from './pages/home-page-pharmacy-administrator/pharmacy-report/pharmacy-report.component';
import { PharmacyProfileComponent } from './pages/home-page-pharmacy-administrator/pharmacy-profile/pharmacy-profile.component';
import { HomePagePharmacyAdministratorComponent } from './pages/home-page-pharmacy-administrator/home-page-pharmacy-administrator.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DemoNgZorroAntdModule } from './ng-zorro-antd.module';
import { AppRoutingModule } from './app-routing.module';
import { HomePageUserComponent } from './pages/home-page-user/home-page-user.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import { HomePagePharmacistComponent } from './pages/home-page-pharmacist/home-page-pharmacist.component';
import { ProfilePharmacistComponent } from './pages/profile-pharmacist/profile-pharmacist.component';
import { UnregisteredUserPageComponent } from './pages/unregistered-user-page/unregistered-user-page.component';
import { ViewPharmaciesComponent } from './pages/view-pharmacies/view-pharmacies.component';
import { SearchFilterPipe } from './pages/view-pharmacies/search-filter.pipe';
import { NzTableModule } from 'ng-zorro-antd/table';

registerLocaleData(en);


@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegistrationPageComponent,
    HomePageUserComponent,
    UserProfileComponent,
    HomePagePharmacistComponent,
    ProfilePharmacistComponent,
    HomePagePharmacyAdministratorComponent,
    PharmacyProfileComponent,
    PharmacyReportComponent,
    MedicineOrderComponent,
    MedicineListComponent,
    PharmacistListComponent,
    UnregisteredUserPageComponent,
    ViewPharmaciesComponent,
    SearchFilterPipe,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DemoNgZorroAntdModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NzTableModule
  ],
  providers: [{ provide: NZ_I18N, useValue: en_US }],
  bootstrap: [AppComponent]
})
export class AppModule { }
