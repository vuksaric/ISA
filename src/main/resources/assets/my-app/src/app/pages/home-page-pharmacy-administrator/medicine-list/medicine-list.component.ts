import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { NzModalService } from 'ng-zorro-antd/modal';

interface Person {
  key: string;
  name: string;
  type: string;
  shape: string;
  manufacturer: string;
  mode: string;
  note: string;
}

@Component({
  selector: 'app-medicine-list',
  templateUrl: './medicine-list.component.html',
  styleUrls: ['./medicine-list.component.css']
})
export class MedicineListComponent implements OnInit {

  isVisible = false;
  search : string;

  constructor(private modal: NzModalService,private toastr: ToastrService) { }

  ngOnInit(): void {
  }

  searchMedicine(){

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


  medicineList: Person[] = [
    {
      key: '1',
      name: 'John Brown',
      type: '32',
      shape: 'New York No. 1 Lake Park',
      manufacturer: 'proba',
      mode:'proba',
      note:'proba'
    },
    {
      key: '2',
      name: 'Jim Green',
      type: '42',
      shape: 'London No. 1 Lake Park',
      manufacturer: 'proba',
      mode:'proba',
      note:'proba'
    },
    {
      key: '3',
      name: 'Joe Black',
      type: '32',
      shape: 'Sidney No. 1 Lake Park',
      manufacturer: 'proba',
      mode:'proba',
      note:'proba'
    }
  ];

}
