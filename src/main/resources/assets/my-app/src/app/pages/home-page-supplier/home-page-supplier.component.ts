import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-home-page-supplier',
  templateUrl: './home-page-supplier.component.html',
  styleUrls: ['./home-page-supplier.component.css']
})
export class HomePageSupplierComponent implements OnInit {

  supplierProfile() {
    this.router.navigate(['supplierHomePage/supplierProfile']);
  }

  newOffer(){
    this.router.navigate(['supplierHomePage/newOffer']);
  }

  viewOffers(){
    this.router.navigate(['supplierHomePage/viewOffers']);
  }

  logOut(){
    localStorage.clear();
    this.router.navigate(['login']);
  }

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  isCollapsed = false;

}
