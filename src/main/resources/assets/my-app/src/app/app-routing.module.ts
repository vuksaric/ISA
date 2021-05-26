import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
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
import { IssuingMedicineComponent } from './pages/issuing-medicine/issuing-medicine.component';
import { ConsultationFrontpageComponent } from './pages/consultation-frontpage/consultation-frontpage.component';
import { ConsultationReportComponent } from './pages/consultation-report/consultation-report.component';
import { SerachPatientsComponent } from './pages/serach-patients/serach-patients.component';

const routes: Routes = [
    //{ path: '', pathMatch: 'full', redirectTo:'login'},
    { path:'', component: UnregisteredUserPageComponent}, 
    { path: 'login', component:LoginPageComponent},
    { path: 'registration', component:RegistrationPageComponent},
    { path: 'homepage', component: HomePageUserComponent},
    { path: 'userProfile', component:UserProfileComponent},
    { path: 'homePagePharmacist', component:HomePagePharmacistComponent},
    { path: 'profilePharmacist', component:ProfilePharmacistComponent},
    { path: 'workSchedule', component:WorkScheduleComponent},
    { path: 'previousConsultations', component:PreviousConsultationsComponent},
    { path: 'vacationRequest', component:VacationRequestComponent},
    { path: 'homePageDermatologist', component:HomePageDermatologistComponent},
    { path: 'viewPharmacies', component:ViewPharmaciesComponent},
    { path: 'issuingMedicine', component:IssuingMedicineComponent},
    { path: 'consultationFrontpage/:id', component:ConsultationFrontpageComponent},
    { path: 'consultationReport/:id', component:ConsultationReportComponent},
    { path: 'searchPatients', component:SerachPatientsComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }