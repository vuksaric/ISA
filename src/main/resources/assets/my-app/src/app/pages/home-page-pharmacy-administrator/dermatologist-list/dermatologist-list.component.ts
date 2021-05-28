import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { NzModalService } from 'ng-zorro-antd/modal';

interface Person {
  key: string;
  name: string;
  surname: string;
  phone: string;
  gender: string;
  birthday: string;
}

@Component({
  selector: 'app-dermatologist-list',
  templateUrl: './dermatologist-list.component.html',
  styleUrls: ['./dermatologist-list.component.css']
})
export class DermatologistListComponent implements OnInit {

  isVisible = false;
  search: string;

  constructor(private modal: NzModalService, private toastr: ToastrService) { }

  ngOnInit(): void {
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

  dermatologistList: Person[] = [
    {
      key: '1',
      name: 'John Brown',
      surname: '32',
      phone: 'New York No. 1 Lake Park',
      gender: 'proba',
      birthday: 'proba'
    },
    {
      key: '2',
      name: 'Jim Green',
      surname: '42',
      phone: 'London No. 1 Lake Park',
      gender: 'proba',
      birthday: 'proba'
    },
    {
      key: '3',
      name: 'Joe Black',
      surname: '32',
      phone: 'Sidney No. 1 Lake Park',
      gender: 'proba',
      birthday: 'proba'
    }
  ];

}
