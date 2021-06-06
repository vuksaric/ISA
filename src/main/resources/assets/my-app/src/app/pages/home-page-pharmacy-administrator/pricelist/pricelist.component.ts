import { PharmacyService } from './../../../services/pharmacy.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pricelist',
  templateUrl: './pricelist.component.html',
  styleUrls: ['./pricelist.component.css']
})
export class PricelistComponent implements OnInit {

  constructor(private pharmacyService : PharmacyService) { }

  ngOnInit(): void {
  }

  getPharmacyMedicine(){
    this.pharmacyService
  }

}
