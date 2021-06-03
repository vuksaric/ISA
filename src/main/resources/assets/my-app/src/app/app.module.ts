import { VacationApprovalComponent } from './pages/home-page-pharmacy-administrator/vacation-approval/vacation-approval.component';
import { PromotionsComponent } from './pages/home-page-pharmacy-administrator/promotions/promotions.component';
import { AdminProfileComponent } from './pages/home-page-pharmacy-administrator/admin-profile/admin-profile.component';
import { PricelistComponent } from './pages/home-page-pharmacy-administrator/pricelist/pricelist.component';
import { DermatologistListComponent } from './pages/home-page-pharmacy-administrator/dermatologist-list/dermatologist-list.component';
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
import { ToastrModule } from 'ngx-toastr';

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
import { WorkScheduleDermatologistComponent } from './pages/work-schedule-dermatologist/work-schedule-dermatologist.component';
import { ExaminationFrontpageComponent } from './pages/examination-frontpage/examination-frontpage.component';
import { ExaminationReportComponent } from './pages/examination-report/examination-report.component';
import { NewConsultationPharmacistComponent } from './pages/new-consultation-pharmacist/new-consultation-pharmacist.component';
import { FilterAllergiesPipe } from './pages/patient-profile/filter-allergies.pipe';
import { ViewFutureExaminationsComponent } from './pages/view-future-examinations/view-future-examinations.component';
import { MedicineReservationComponent } from './pages/medicine-reservation/medicine-reservation.component';

import { DatePipe } from '@angular/common';
import { ViewReservationsComponent } from './pages/view-reservations/view-reservations.component';
import { NewConsultationPatientComponent } from './pages/new-consultation-patient/new-consultation-patient.component';
import { ViewSubscribedPharmaciesComponent } from './pages/view-subscribed-pharmacies/view-subscribed-pharmacies.component';
import { PreviousExaminationsComponent } from './pages/previous-examinations/previous-examinations.component';
import { ViewReviewsComponent } from './pages/view-reviews/view-reviews.component';
import { ViewPreviousExaminationsComponent } from './pages/view-previous-examinations/view-previous-examinations.component';
import { NewExaminationDermatologistComponent } from './pages/new-examination-dermatologist/new-examination-dermatologist.component';
import { ProfilePatientDoctorComponent } from './pages/profile-patient-doctor/profile-patient-doctor.component';
import { ViewPreviousConsultationsProfileComponent } from './pages/view-previous-consultations-profile/view-previous-consultations-profile.component';
import { ViewPreviousExaminationsProfileComponent } from './pages/view-previous-examinations-profile/view-previous-examinations-profile.component'
import { AddMedicineComponent } from './pages/add-medicine/add-medicine.component';
import { HomePageSupplierComponent } from './pages/home-page-supplier/home-page-supplier.component';
import { ViewPreviousConsultationsComponent } from './pages/view-previous-consultations/view-previous-consultations.component';
import { ViewFutureConsultationsComponent } from './pages/view-future-consultations/view-future-consultations.component';
import { ViewErecipesComponent } from './pages/view-erecipes/view-erecipes.component';
import { ViewErecipeMedicinesComponent } from './pages/view-erecipe-medicines/view-erecipe-medicines.component';
import { SearchMedicinePipe } from './pages/medicine-reservation/search-medicine.pipe';

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
    DermatologistListComponent,
    PricelistComponent,
    AdminProfileComponent,
    WorkScheduleDermatologistComponent,
    ExaminationFrontpageComponent,
    ExaminationReportComponent,
    NewConsultationPharmacistComponent,
    FilterAllergiesPipe,
    ViewFutureExaminationsComponent,
    MedicineReservationComponent,
    ViewReservationsComponent,
    NewConsultationPatientComponent,
    ViewSubscribedPharmaciesComponent,
    PromotionsComponent,
    VacationApprovalComponent,
    PreviousExaminationsComponent,
    ViewReviewsComponent,
    ViewPreviousExaminationsComponent,
    NewExaminationDermatologistComponent,
    ProfilePatientDoctorComponent,
    ViewPreviousConsultationsProfileComponent,
    ViewPreviousExaminationsProfileComponent,
    AddMedicineComponent,
    HomePageSupplierComponent,
    ViewPreviousConsultationsComponent,
    ViewFutureConsultationsComponent,
    ViewErecipesComponent,
    ViewErecipeMedicinesComponent,
    SearchMedicinePipe,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DemoNgZorroAntdModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NzTableModule,
    ToastrModule.forRoot(),
  ],
  providers: [{ provide: NZ_I18N, useValue: en_US }, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
