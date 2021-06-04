import { VacationApprovalComponent } from './pages/home-page-pharmacy-administrator/vacation-approval/vacation-approval.component';
import { PromotionsComponent } from './pages/home-page-pharmacy-administrator/promotions/promotions.component';
import { AdminProfileComponent } from './pages/home-page-pharmacy-administrator/admin-profile/admin-profile.component';
import { PricelistComponent } from './pages/home-page-pharmacy-administrator/pricelist/pricelist.component';
import { DermatologistListComponent } from './pages/home-page-pharmacy-administrator/dermatologist-list/dermatologist-list.component';
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
import { ViewMadeExaminationsComponent } from './pages/view-made-examinations/view-made-examinations.component';
import { PatientProfileComponent } from './pages/patient-profile/patient-profile.component';
import { IssuingMedicineComponent } from './pages/issuing-medicine/issuing-medicine.component';
import { ConsultationFrontpageComponent } from './pages/consultation-frontpage/consultation-frontpage.component';
import { ConsultationReportComponent } from './pages/consultation-report/consultation-report.component';
import { SerachPatientsComponent } from './pages/serach-patients/serach-patients.component';
import { RegistrationPharmacyComponent } from './pages/registration-pharmacy/registration-pharmacy.component';
import { RegistrationAdminComponent } from './pages/registration-admin/registration-admin.component';
import { WorkScheduleDermatologistComponent } from './pages/work-schedule-dermatologist/work-schedule-dermatologist.component';
import { ExaminationFrontpageComponent } from './pages/examination-frontpage/examination-frontpage.component';
import { ExaminationReportComponent } from './pages/examination-report/examination-report.component';
import { ViewFutureExaminationsComponent } from './pages/view-future-examinations/view-future-examinations.component';
import { MedicineReservationComponent } from './pages/medicine-reservation/medicine-reservation.component';
import { ViewReservationsComponent } from './pages/view-reservations/view-reservations.component';
import { NewConsultationPatientComponent } from './pages/new-consultation-patient/new-consultation-patient.component';
import { ViewSubscribedPharmaciesComponent } from './pages/view-subscribed-pharmacies/view-subscribed-pharmacies.component';
import { NewConsultationPharmacistComponent } from './pages/new-consultation-pharmacist/new-consultation-pharmacist.component';
import { PreviousExaminationsComponent } from './pages/previous-examinations/previous-examinations.component';
import { ViewReviewsComponent } from './pages/view-reviews/view-reviews.component';
import { ViewPreviousExaminationsComponent } from './pages/view-previous-examinations/view-previous-examinations.component';
import { NewExaminationDermatologistComponent } from './pages/new-examination-dermatologist/new-examination-dermatologist.component';
import { ProfilePatientDoctorComponent } from './pages/profile-patient-doctor/profile-patient-doctor.component';
import { ViewPreviousExaminationsProfileComponent } from './pages/view-previous-examinations-profile/view-previous-examinations-profile.component';
import { AddMedicineComponent } from './pages/add-medicine/add-medicine.component';
import { HomePageSupplierComponent } from './pages/home-page-supplier/home-page-supplier.component';

import { ViewPreviousConsultationsComponent } from './pages/view-previous-consultations/view-previous-consultations.component';
import { ViewFutureConsultationsComponent } from './pages/view-future-consultations/view-future-consultations.component';
import { ViewErecipesComponent } from './pages/view-erecipes/view-erecipes.component';
import { ViewErecipeMedicinesComponent } from './pages/view-erecipe-medicines/view-erecipe-medicines.component';
import { SupplierProfileComponent } from './pages/supplier-profile/supplier-profile.component';
import { NewOfferComponent } from './pages/new-offer/new-offer.component';
import { ViewOffersComponent } from './pages/view-offers/view-offers.component';

const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo: 'pharmacyAdmin' },
    { path: 'login', component: LoginPageComponent },
    { path: 'registration', component: RegistrationPageComponent },
    { path: 'un', component: UnregisteredUserPageComponent },
    { path: 'sysadminhome', component:SystemAdminHomePageComponent},
    { path: 'homepage', component: HomePageUserComponent, children:[
        { path: 'userProfile', component:UserProfileComponent},
        { path: 'patientProfile', component: PatientProfileComponent},
        { path: 'viewMadeExaminations', component: ViewMadeExaminationsComponent},
        { path: 'viewFutureExaminations', component: ViewFutureExaminationsComponent},
        { path: 'viewPreviousExaminations', component: ViewPreviousExaminationsComponent},
        { path: 'viewPharmacies', component:ViewPharmaciesComponent},
        { path: 'viewSubsribedPharmacies', component:ViewSubscribedPharmaciesComponent},
        { path: 'viewReservations', component: ViewReservationsComponent},
        { path: 'reserveMedicine', component: MedicineReservationComponent},
        { path: 'newConsultationPatient', component: NewConsultationPatientComponent},
        { path: 'viewReviews', component: ViewReviewsComponent},
        { path: 'viewPreviousConsultations', component: ViewPreviousConsultationsComponent},
        { path: 'viewFutureConsultations', component: ViewFutureConsultationsComponent},
        { path: 'viewERecipes', component: ViewErecipesComponent},
        { path: 'viewERecipeMedicines', component: ViewErecipeMedicinesComponent}

    ]},
    { path: 'homePagePharmacist', component: HomePagePharmacistComponent },
    { path: 'profilePharmacist', component: ProfilePharmacistComponent },
    { path: 'workSchedule', component:WorkScheduleComponent},
    { path: 'workScheduleDermatologist', component:WorkScheduleDermatologistComponent},
    { path: 'previousConsultations', component:PreviousConsultationsComponent},
    { path: 'previousExaminations', component:PreviousExaminationsComponent},
    { path: 'vacationRequest', component:VacationRequestComponent},
    { path: 'homePageDermatologist', component:HomePageDermatologistComponent},
    { path: 'issuingMedicine', component:IssuingMedicineComponent},
    { path: 'consultationFrontpage/:id', component:ConsultationFrontpageComponent},
    { path: 'examinationFrontpage/:id', component:ExaminationFrontpageComponent},
    { path: 'consultationReport/:id', component:ConsultationReportComponent},
    { path: 'examinationReport/:id', component:ExaminationReportComponent},
    { path: 'newConsultationPharmacist/:id', component:NewConsultationPharmacistComponent},
    { path: 'newExaminationDermatologist/:id', component:NewExaminationDermatologistComponent},
    { path: 'searchPatients', component:SerachPatientsComponent},
    { path: 'registrationPharmacy', component:RegistrationPharmacyComponent},
    { path: 'registrationAdmin', component:RegistrationAdminComponent},
    { path: 'patientProfileDoctor/:id', component:ProfilePatientDoctorComponent},
    { path: 'viewPreviousExaminatiosProfile/:id', component:ViewPreviousExaminationsProfileComponent},
    { path: 'userProfile', component:UserProfileComponent},
    { path: 'addMedicine', component:AddMedicineComponent},
    { path: 'pharmacyAdmin', component: HomePagePharmacyAdministratorComponent, children: [
        { path: 'pharmacy-profile', component: PharmacyProfileComponent},
        { path: 'pharmacy-report', component: PharmacyReportComponent },
        { path: 'medicine-list', component: MedicineListComponent },
        { path: 'medicine-order', component: MedicineOrderComponent},
        { path: 'pharmacist-list', component: PharmacistListComponent },
        { path: 'dermatologist-list', component:DermatologistListComponent},
        { path: 'pricelist', component:PricelistComponent},
        { path: 'admin-profile', component:AdminProfileComponent},
        { path: 'promotions', component:PromotionsComponent},
        { path: 'vacation-approval', component:VacationApprovalComponent}
    ] },
    { path: 'supplierHomePage', component: HomePageSupplierComponent, children:[
        { path: 'supplierProfile', component:SupplierProfileComponent},
        { path: 'newOffer', component:NewOfferComponent},
        { path: 'viewOffers', component:ViewOffersComponent},
    ]},
    
]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }