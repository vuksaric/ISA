import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page-pharmacy-administrator',
  templateUrl: './home-page-pharmacy-administrator.component.html',
  styleUrls: ['./home-page-pharmacy-administrator.component.css']
})
export class HomePagePharmacyAdministratorComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
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

  }
  medicine() {
    this.router.navigate(['pharmacyAdmin/medicine-list']);
  }
  medicineOrder() {
    this.router.navigate(['pharmacyAdmin/medicine-order']);
  }
  medicineAllOrders() {
    //this.router.navigate(['pharmacyAdmin/pharmacist-list']);
  }
  pharmacist() {
    this.router.navigate(['/pharmacyAdmin/pharmacist-list']);
  }
  dermatologist() {
    this.router.navigate(['/pharmacyAdmin/dermatologist-list']);
  }
  freeAppointments() {
    this.router.navigate(['/pharmacyAdmin/pharmacy-profile']);
  }
  adminProfile(){
    this.router.navigate(['/pharmacyAdmin/admin-profile']);
  }




}
