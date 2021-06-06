import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page-pharmacy-administrator',
  templateUrl: './home-page-pharmacy-administrator.component.html',
  styleUrls: ['./home-page-pharmacy-administrator.component.css']
})
export class HomePagePharmacyAdministratorComponent implements OnInit {

  constructor(private router: Router, private authService : AuthService) { }

  ngOnInit(): void {
    this.authService.checkAuthPharmacyAdmin();
  }

  logout(){
    this.router.navigate(['login']);
    localStorage.clear();
  }

  pharmacyProfile() {
    this.router.navigate(['pharmacyAdmin/pharmacy-profile']);
  }
  pharmacyReport() {
    this.router.navigate(['pharmacyAdmin/pharmacy-report']);
  }

  pharmacyPricelist(){
    this.router.navigate(['/pharmacyAdmin/pricelist']);
  }
  pharmacyVacations(){
    this.router.navigate(['/pharmacyAdmin/vacation-approval']);
  }
  medicine() {
    this.router.navigate(['pharmacyAdmin/medicine-list']);
  }
  medicineOrder() {
    this.router.navigate(['pharmacyAdmin/medicine-order']);
  }
  medicineAllOrders() {
    this.router.navigate(['pharmacyAdmin/missing-drugs']);
  }
  pharmacist() {
    this.router.navigate(['/pharmacyAdmin/pharmacist-list']);
  }
  dermatologist() {
    this.router.navigate(['/pharmacyAdmin/dermatologist-list']);
  }
  freeAppointments() {
    this.router.navigate(['/pharmacyAdmin/free-appointments']);
  }
  adminProfile(){
    this.router.navigate(['/pharmacyAdmin/admin-profile']);
  }
  promotions(){
    this.router.navigate(['/pharmacyAdmin/promotions']);
  }




}
