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
import { ViewFutureExaminationsComponent } from './pages/view-future-examinations/view-future-examinations.component';
import { MedicineReservationComponent } from './pages/medicine-reservation/medicine-reservation.component';
import { ViewReservationsComponent } from './pages/view-reservations/view-reservations.component';
import { NewConsultationPatientComponent } from './pages/new-consultation-patient/new-consultation-patient.component';
import { ViewSubscribedPharmaciesComponent } from './pages/view-subscribed-pharmacies/view-subscribed-pharmacies.component';

const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo: 'pharmacyAdmin' },
    { path: 'login', component: LoginPageComponent },
    { path: 'registration', component: RegistrationPageComponent },
    { path: 'sysadminhome', component:SystemAdminHomePageComponent},
    { path: 'homepage', component: HomePageUserComponent, children:[
        { path: 'userProfile', component:UserProfileComponent},
        { path: 'patientProfile', component: PatientProfileComponent},
        { path: 'viewMadeExaminations', component: ViewMadeExaminationsComponent},
        { path: 'viewFutureExaminations', component: ViewFutureExaminationsComponent},
        { path: 'viewPharmacies', component:ViewPharmaciesComponent},
        { path: 'viewSubsribedPharmacies', component:ViewSubscribedPharmaciesComponent},
        { path: 'viewReservations', component: ViewReservationsComponent},
        { path: 'reserveMedicine', component: MedicineReservationComponent},
        { path: 'newConsultationPatient', component: NewConsultationPatientComponent}

    ]},
    //{ path: 'userProfile', component:UserProfileComponent},
    { path: 'homePagePharmacist', component: HomePagePharmacistComponent },
    { path: 'profilePharmacist', component: ProfilePharmacistComponent },
    { path: 'workSchedule', component:WorkScheduleComponent},
    { path: 'previousConsultations', component:PreviousConsultationsComponent},
    { path: 'vacationRequest', component:VacationRequestComponent},
    { path: 'homePageDermatologist', component:HomePageDermatologistComponent},
    //{ path: 'viewPharmacies', component:ViewPharmaciesComponent},
    //{ path: 'viewMadeExaminations', component: ViewMadeExaminationsComponent},
    //{ path: 'viewFutureExaminations', component: ViewFutureExaminationsComponent},
    //{ path: 'viewReservations', component: ViewReservationsComponent},
    //{ path: 'patientProfile', component: PatientProfileComponent},
    //{ path: 'reserveMedicine', component: MedicineReservationComponent},
    { path: 'issuingMedicine', component:IssuingMedicineComponent},
    { path: 'consultationFrontpage/:id', component:ConsultationFrontpageComponent},
    { path: 'consultationReport/:id', component:ConsultationReportComponent},
    { path: 'searchPatients', component:SerachPatientsComponent},
    { path: 'registrationPharmacy', component:RegistrationPharmacyComponent},
    { path: 'registrationAdmin', component:RegistrationAdminComponent},
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