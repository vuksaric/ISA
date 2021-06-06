import { AuthService } from 'src/app/services/auth.service';
import { MedicineQuantityService } from './../../../services/medicine-quantity.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { NzModalService } from 'ng-zorro-antd/modal';
import { PharmacyService } from 'src/app/services/pharmacy.service';


@Component({
  selector: 'app-medicine-list',
  templateUrl: './medicine-list.component.html',
  styleUrls: ['./medicine-list.component.css']
})
export class MedicineListComponent implements OnInit {

  isVisible = false;
  search: string;
  medicineList: any[] = [];
  medicineDifference: any[] = [];
  medicineId: number;
  pharmacyId : any;

  constructor(private modal: NzModalService, private toastr: ToastrService, private pharmacyService: PharmacyService, private medicineQuantityService: MedicineQuantityService, private authService : AuthService) { }

  ngOnInit(): void {
    let token = this.authService.getDataFromToken();
    this.pharmacyId = token.pharmacyId.toString(); 
    this.getMedicineDifference();
    this.getMedicineFromPharmacy();

  }

  searchMedicine() {

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

  getMedicineFromPharmacy() {
    this.pharmacyService.getMedicineInPharmacy(this.pharmacyId).subscribe(data => {
      this.medicineList = data;
    })
  }

  addMedicineQuantity() {
    this.pharmacyService.addMedicineQuantity(this.medicineId,this.pharmacyId, null).subscribe(data => {
      this.isVisible = false;
      this.getMedicineFromPharmacy();
      this.getMedicineDifference();
    })
  }

  getMedicineDifference() {
    this.medicineQuantityService.getMedicineDifference(this.pharmacyId).subscribe(data => {
      this.medicineDifference = data;
    })
  }

  searchMedicineQuantity() {
    if(this.search === ''){
      this.getMedicineFromPharmacy();
    }
    else{
      this.pharmacyService.search(this.search, this.pharmacyId).subscribe(data => {
        this.medicineList = data;
      })
    }
  }

  removeMedicineQuantity(id) {
    this.pharmacyService.removeMedicineQuantity(id, this.pharmacyId).subscribe(data => {
      this.medicineDifference = data;
      this.getMedicineFromPharmacy();
      this.getMedicineDifference();
    })
  }

  showDeleteConfirm(data): void {
    this.modal.confirm({
      nzTitle: 'Are you sure delete this pharmacist?',
      nzOkText: 'Yes',
      nzOkType: 'primary',
      nzOkDanger: true,
      nzOnOk: () => this.removeMedicineQuantity(data.id),
      nzCancelText: 'No',
      nzOnCancel: () => console.log('Cancel')
    });
  }

}
