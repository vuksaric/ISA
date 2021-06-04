import { Component, OnInit } from '@angular/core';
import { GeocoderService } from 'angular-geocoder';
import { NzModalService } from 'ng-zorro-antd/modal';
import { PharmacistService } from 'src/app/services/pharmacist.service';
import { PharmacyService } from 'src/app/services/pharmacy.service';

@Component({
  selector: 'app-pharmacy-profile',
  templateUrl: './pharmacy-profile.component.html',
  styleUrls: ['./pharmacy-profile.component.css']
})
export class PharmacyProfileComponent implements OnInit {

  latitude = 45.267136;
  longitude = 19.833549;
  isVisible = false;
  name: string;
  address: string;
  description: string;
  mark: number;
  listOfWorkers: Worker[];
  list2: Worker[] = [];
  listOfMedicines : any[] = [];

  constructor(private geocoderService: GeocoderService, private modal: NzModalService, private pharmacyService: PharmacyService) { }

  ngOnInit(): void {
    this.getMedicines();
    this.getMark();
    this.getAllPharmacists();
    this.getPharmacy();
  }

  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    this.isVisible = false;
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  getAllPharmacists() {
    this.pharmacyService.getPharmacistsFromPharmacy('1').subscribe(data => {
      for(let element of data){
        this.list2.push(element);
      }
      this.getAllDermatologists();
    })
  }

  getAllDermatologists() {
    this.pharmacyService.getDermatologistsFromPharmacy('1').subscribe(data => {
      for(let element of data){
        this.list2.push(element);
      }
      this.listOfWorkers = this.list2;
    })
  }

  getMark() {
    this.pharmacyService.getPharmacyMark('1').subscribe(data => {
      this.mark = data;
    })
  }

  getMedicines() {
    this.pharmacyService.getMedicineFromPharmacy('1').subscribe(data => {
      this.listOfMedicines = data;
    })
  }

  getPharmacy() {
    this.pharmacyService.getPharmacy('1').subscribe(data => {
      this.name = data.name;
      this.address = data.street + ', ' + data.town + ', ' + data.state;
      this.description = data.description;
    })
  }


}
