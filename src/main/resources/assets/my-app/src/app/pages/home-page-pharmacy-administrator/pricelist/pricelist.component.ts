import { PricelistService } from './../../../services/pricelist.service';
import { AuthService } from 'src/app/services/auth.service';
import { PharmacyService } from './../../../services/pharmacy.service';
import { NzModalService } from 'ng-zorro-antd/modal';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pricelist',
  templateUrl: './pricelist.component.html',
  styleUrls: ['./pricelist.component.css']
})
export class PricelistComponent implements OnInit {

  pharmacyId : any;
  priceList : any[] = [];
  isVisible = false;
  duration : any;
  price : any;
  currentMedicine : any;

  constructor(private pharmacyService : PharmacyService, private authService : AuthService, private pricelistService : PricelistService,private modal: NzModalService) { }

  ngOnInit(): void {
    let token = this.authService.getDataFromToken();
    this.pharmacyId = token.pharmacyId.toString(); 

    this.getPriceList();
  }

  getPriceList(){
    this.pricelistService.getPricelist(this.pharmacyId).subscribe(data => {
      console.log(data);
      for(let el of data){
        el.start = el.start.substring(0,10);
        el.end = el.end.substring(0,10);
      }

      this.priceList = data;
    })
  }

  showModal(data): void {
    this.currentMedicine = data;
    this.price = data.price;
    this.isVisible = true;
  }

  handleOk(): void {
    const body = {
      pharmacyId : this.currentMedicine.pharmacyId,
      id : this.currentMedicine.id,
      price : this.price,
      duration : this.duration
    }

    this.pricelistService.editMedicine(body).subscribe(data => {
      this.getPriceList();
      this.isVisible = false;
    })

  }

  handleCancel(): void {
    this.isVisible = false;
  }

}
