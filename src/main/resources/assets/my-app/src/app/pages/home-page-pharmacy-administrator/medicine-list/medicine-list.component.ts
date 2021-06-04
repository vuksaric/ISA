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

  constructor(private modal: NzModalService, private toastr: ToastrService, private pharmacyService: PharmacyService, private medicineQuantityService: MedicineQuantityService) { }

  ngOnInit(): void {
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
    this.pharmacyService.getMedicineFromPharmacy('1').subscribe(data => {
      this.medicineList = data;
    })
  }

  addMedicineQuantity() {
    this.pharmacyService.addMedicineQuantity(this.medicineId, '1', null).subscribe(data => {
      this.isVisible = false;
    })
  }

  getMedicineDifference() {
    this.medicineQuantityService.getMedicineDifference('1').subscribe(data => {
      this.medicineDifference = data;
    })
  }

  searchMedicineQuantity() {
    this.pharmacyService.search(this.search, '1').subscribe(data => {

    })
  }

  removeMedicineQuantity(data) {
    this.pharmacyService.removeMedicineQuantity(this.search, '1').subscribe(data => {

    })
  }

  showDeleteConfirm(): void {
    this.modal.confirm({
      nzTitle: 'Are you sure delete this pharmacist?',
      nzOkText: 'Yes',
      nzOkType: 'primary',
      nzOkDanger: true,
      nzOnOk: () => console.log('OK'),
      nzCancelText: 'No',
      nzOnCancel: () => console.log('Cancel')
    });
  }

}
