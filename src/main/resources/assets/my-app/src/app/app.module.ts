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

import { SystemAdminHomePageComponent } from './pages/system-admin-home-page/system-admin-home-page.component';
import { HomePageUserComponent } from './pages/home-page-user/home-page-user.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import { HomePagePharmacistComponent } from './pages/home-page-pharmacist/home-page-pharmacist.component';
import { ProfilePharmacistComponent } from './pages/profile-pharmacist/profile-pharmacist.component';
import { WorkScheduleComponent } from './pages/work-schedule/work-schedule.component';
import { PreviousConsultationsComponent } from './pages/previous-consultations/previous-consultations.component';
import { VacationRequestComponent } from './pages/vacation-request/vacation-request.component';
import { HomePageDermatologistComponent } from './pages/home-page-dermatologist/home-page-dermatologist.component';
import { UnregisteredUserPageComponent } from './pages/unregistered-user-page/unregistered-user-page.component';
import { ViewPharmaciesComponent } from './pages/view-pharmacies/view-pharmacies.component';
import { SearchFilterPipe } from './pages/view-pharmacies/search-filter.pipe';
import { ViewMadeExaminationsComponent } from './pages/view-made-examinations/view-made-examinations.component';
import { PatientProfileComponent } from './pages/patient-profile/patient-profile.component';
import { IssuingMedicineComponent } from './pages/issuing-medicine/issuing-medicine.component';
import { ConsultationFrontpageComponent } from './pages/consultation-frontpage/consultation-frontpage.component';
import { ConsultationReportComponent } from './pages/consultation-report/consultation-report.component';
import { SerachPatientsComponent } from './pages/serach-patients/serach-patients.component';
import { RegistrationPharmacyComponent } from './pages/registration-pharmacy/registration-pharmacy.component';
import { RegistrationAdminComponent } from './pages/registration-admin/registration-admin.component';

import { NzTableModule } from 'ng-zorro-antd/table';
import { FilterAllergiesPipe } from './pages/patient-profile/filter-allergies.pipe';
import { ViewFutureExaminationsComponent } from './pages/view-future-examinations/view-future-examinations.component';
import { MedicineReservationComponent } from './pages/medicine-reservation/medicine-reservation.component';

import { DatePipe } from '@angular/common'

registerLocaleData(en);


@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegistrationPageComponent,
    SystemAdminHomePageComponent,
    HomePageUserComponent,
    UserProfileComponent,
    HomePagePharmacistComponent,
    ProfilePharmacistComponent,
    WorkScheduleComponent,
    PreviousConsultationsComponent,
    VacationRequestComponent,
    HomePageDermatologistComponent,
    HomePagePharmacyAdministratorComponent,
    PharmacyProfileComponent,
    PharmacyReportComponent,
    MedicineOrderComponent,
    MedicineListComponent,
    PharmacistListComponent,
    UnregisteredUserPageComponent,
    ViewPharmaciesComponent,
    SearchFilterPipe,
    ViewMadeExaminationsComponent,
    PatientProfileComponent,
    IssuingMedicineComponent,
    ConsultationFrontpageComponent,
    ConsultationReportComponent,
    SerachPatientsComponent,
    RegistrationPharmacyComponent,
    RegistrationAdminComponent,
    FilterAllergiesPipe,
    ViewFutureExaminationsComponent,
    MedicineReservationComponent,
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
  providers: [{ provide: NZ_I18N, useValue: en_US }, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
