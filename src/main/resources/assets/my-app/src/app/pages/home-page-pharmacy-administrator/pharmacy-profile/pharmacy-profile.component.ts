import { AuthService } from 'src/app/services/auth.service';
import { ExaminationService } from './../../../services/examination.service';
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

  latitude : any;
  longitude : any;
  isVisible = false;
  name: string;
  address: string;
  description: string;
  mark: number;
  listOfWorkers: Worker[];
  list2: Worker[] = [];
  listOfMedicines : any[] = [];
  listOfFreePeriods : any[] = [];
  pharmacyId : any;

  constructor(private geocoderService: GeocoderService, private modal: NzModalService, private pharmacyService: PharmacyService, private examinationService : ExaminationService, private authService : AuthService) { }

  ngOnInit(): void {
    let token = this.authService.getDataFromToken();
    this.pharmacyId = token.pharmacyId.toString(); 

    this.getMedicines();
    this.getMark();
    this.getAllPharmacists();
    this.getPharmacy();
    this.getFreeExamination();
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
    this.pharmacyService.getPharmacistsFromPharmacy(this.pharmacyId).subscribe(data => {
      for(let element of data){
        this.list2.push(element);
      }
      this.getAllDermatologists();
    })
  }

  getAllDermatologists() {
    this.pharmacyService.getDermatologistsFromPharmacy(this.pharmacyId).subscribe(data => {
      for(let element of data){
        this.list2.push(element);
      }
      this.listOfWorkers = this.list2;
    })
  }

  getMark() {
    this.pharmacyService.getPharmacyMark(this.pharmacyId).subscribe(data => {
      this.mark = data;
    })
  }

  getMedicines() {
    this.pharmacyService.getMedicineFromPharmacy(this.pharmacyId).subscribe(data => {
      this.listOfMedicines = data;
    })
  }

  getPharmacy() {
    this.pharmacyService.getPharmacy(this.pharmacyId).subscribe(data => {
      this.name = data.name;
      this.latitude = data.latitude;
      this.longitude = data.longitude;
      console.log(this.latitude && this.longitude);
      this.address = data.street + ', ' + data.town + ', ' + data.state;
      this.description = data.description;
    })
  }

  getFreeExamination(){
    this.examinationService.freeExaminationsByPharmacy(this.pharmacyId).subscribe(data => {
      this.listOfFreePeriods = data;
    })
  }


}
